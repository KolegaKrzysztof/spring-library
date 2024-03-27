package pl.edu.wszib.libraryapp.services;

import pl.edu.wszib.libraryapp.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> getById(int id);

    List<Book> getAll();

    List<Book> getByPattern(String pattern);

    void persist(Book book);

    void setInDb();

    void update(Book book);
}