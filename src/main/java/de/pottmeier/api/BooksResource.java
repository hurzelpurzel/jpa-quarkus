package de.pottmeier.api;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import model.Book;
import model.BookRepository;

@Path("/api/books")
public class BooksResource {

	@Inject
	BookRepository resource;

	@Inject
	BookMapper mapper;

	@GET
	@Path("{id}")
	@Produces("application/json")
	public BookDto get(@PathParam("id") Integer id) {
		Book book = resource.findById(id);
		if (book == null) {
			throw new WebApplicationException(404);
		}
		return mapper.toResource(book);
	}

	
	@GET
	@Produces("application/json")
	public Response list() {
		List<Book> b = resource.listAll();
		// ... build a response with page links and return a 200 response with a list
		List<BookDto> books = mapper.toResources(b);
		return Response.ok(books).build();
    }

	
	
	@Transactional
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response add(BookDto BookToSave, @Context UriInfo uriInfo) {
		Book book = mapper.toModel(BookToSave);
		resource.persist(book);

		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		uriBuilder.path(Integer.toString(book.getId()));
		return Response.created(uriBuilder.build()).build();
		// ... build a new location URL and return 201 response with an entity

	}

	@Transactional
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response update(@PathParam("id") Integer id, BookDto BookToSave) {
		Book book = mapper.toModel(BookToSave);
		if (resource.findById(id) == null) {
			resource.persist(book);
			return Response.status(204).build();
		}
		resource.persist(book);
		return Response.status(201).build();
		// ... build a new location URL and return 201 response with an entity
	}

	@Transactional
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		if (!resource.deleteById(id)) {
			throw new WebApplicationException(404);
		}
	}

}
