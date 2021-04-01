package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


/**
 * The persistent class for the books database table.
 * see: https://quarkus.io/guides/hibernate-orm-panache
 */
@Entity
@Table(name="books")
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book extends PanacheEntityBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="Author")
	private String author;

	@Column(name="Genre")
	private String genre;

	@Column(name="Height")
	private int height;

	@Column(name="Publisher")
	private String publisher;

	@Column(name="Title")
	private String title;

	public Book() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}