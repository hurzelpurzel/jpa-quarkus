package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-03-30T09:33:26.368+0200")
@StaticMetamodel(Book.class)
public class Book_ {
	public static volatile SingularAttribute<Book, Integer> id;
	public static volatile SingularAttribute<Book, String> author;
	public static volatile SingularAttribute<Book, String> genre;
	public static volatile SingularAttribute<Book, Integer> height;
	public static volatile SingularAttribute<Book, String> publisher;
	public static volatile SingularAttribute<Book, String> title;
}
