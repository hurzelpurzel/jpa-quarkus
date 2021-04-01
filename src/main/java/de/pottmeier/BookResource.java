package de.pottmeier;

import javax.ws.rs.Path;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import model.Book;


@Path("/books-resource")
public interface BookResource extends PanacheEntityResource<Book, Integer>  {

}
