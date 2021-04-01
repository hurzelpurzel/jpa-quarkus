package model;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class BookRepository implements PanacheRepositoryBase<Book, Integer> {
 
}
