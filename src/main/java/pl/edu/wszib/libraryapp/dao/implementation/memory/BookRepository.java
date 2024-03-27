package pl.edu.wszib.libraryapp.dao.implementation.memory;

import org.springframework.stereotype.Component;
import pl.edu.wszib.libraryapp.dao.IBookDAO;
import pl.edu.wszib.libraryapp.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class BookRepository implements IBookDAO {
    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        this.books.add(new Book(1, "book 1",
                "author 1", "978-1617297571", true));
        this.books.add(new Book(2, "book 2",
                "author 2", "978-1473226401", true));
        this.books.add(new Book(3, "book 3",
                "author 3", "978-0137035151", true));
        this.books.add(new Book(4, "book 4",
                "author 4", "978-1501142970", true));
    }

    @Override
    public Optional<Book> getById(final int id) {
        return this.books.stream().filter(book -> book.getId() == id).findFirst();
    }

    @Override
    public List<Book> getAll() {
        return this.books;
    }

    @Override
    public void delete(final int id) {
        this.books.removeIf(book -> book.getId() == id);
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return this.books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(pattern.toLowerCase()) ||
                        book.getTitle().toLowerCase().contains(pattern.toLowerCase()))
                .toList();
    }

    @Override
    public void persist(Book book) {

    }
}