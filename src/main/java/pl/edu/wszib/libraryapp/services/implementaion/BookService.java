package pl.edu.wszib.libraryapp.services.implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.libraryapp.dao.IBookDAO;
import pl.edu.wszib.libraryapp.model.Book;
import pl.edu.wszib.libraryapp.services.IBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDAO;

    public Optional<Book> getById(int id) {
        return this.bookDAO.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookDAO.getAll();
    }

    @Override
    public List<Book> getByPattern(String pattern) {
        return bookDAO.getByPattern(pattern);
    }

    public void persist(Book book) {
        this.bookDAO.persist(book);
    }

    @Override
    public void setInDb() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "book 1",
                "author 1", "978-1617297571", true));
        books.add(new Book(2, "book 2",
                "author 2", "978-1473226401", true));
        books.add(new Book(3, "book 3",
                "author 3", "978-0137035151", true));
        books.add(new Book(4, "book 4",
                "author 4", "978-1501142970", true));

        for (Book book : books) {
            this.bookDAO.persist(book);
        }
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }
}
