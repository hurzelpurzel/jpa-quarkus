package de.pottmeier.api;

import java.util.List;

import org.mapstruct.Mapper;

import model.Book;

@Mapper(componentModel = "cdi")
public interface BookMapper {


	BookDto toResource(Book book);
	Book toModel(BookDto book);
	
	List<BookDto> toResources(List<Book> books);
}
