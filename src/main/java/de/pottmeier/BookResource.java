package de.pottmeier;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import model.Book;

public interface BookResource extends PanacheEntityResource<Book, Integer>  {

}
