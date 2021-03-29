package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Consumer;



import org.flywaydb.core.api.migration.Context;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class V2__LoadCSV extends BaseJavaMigration {

	@Override
	public void migrate(Context context) throws Exception {
		// TODO Auto-generated method stub
		InputStream inst = V2__LoadCSV.class.getClassLoader().getResourceAsStream("book.csv");
	    Reader rd = new BufferedReader(new java.io.InputStreamReader(inst));
		final CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(false).build();
		final CSVReader reader = new CSVReaderBuilder(rd).withSkipLines(1).withCSVParser(parser).build();
		DBWriter c = new DBWriter(context.getConnection());
		c.prepare(10);
		reader.forEach(c);
		c.closeAll();

	}

	private static class DBWriter implements Consumer<String[]> {

		private Connection connection;
		private static String sql = "insert into books (Title,Author,Genre,Height,Publisher) values (?, ?, ?,?,?)";
		private PreparedStatement ps;
		private int batchsize =0;
		private int count = 0;

		public DBWriter(Connection connection) {
			super();
			this.connection = connection;

		}

		public void prepare(int batchsize) throws SQLException {
			this.ps = connection.prepareStatement(sql);
			this.batchsize = batchsize;
		}

		public void closeAll() throws SQLException {
			ps.executeBatch(); // orphans
			ps.close();
		}

		@Override
		public void accept(String[] line) {

			int Title = 0;
			int Author = 1;
			int Genre = 2;
			int Height = 3;
			int Publisher = 4;

			try {
				ps.setString(1, line[Title]);

				ps.setString(2, line[Author]);
				ps.setString(3, line[Genre]);
				ps.setInt(4, Integer.parseInt(line[Height]));
				ps.setString(5, line[Publisher]);

				ps.addBatch();
				if (++count % batchsize == 0) {
					ps.executeBatch();
				}
			} catch (SQLException e) {
				throw new  RuntimeException(e);
			}
		}

	}

}
