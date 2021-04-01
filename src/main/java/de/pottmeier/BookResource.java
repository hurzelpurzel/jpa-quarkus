package de.pottmeier;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import model.Book;
import model.BookRepository;



public interface BookResource extends PanacheRepositoryResource<BookRepository,Book, Integer>  {

}
