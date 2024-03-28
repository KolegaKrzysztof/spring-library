package pl.edu.wszib.libraryapp.services.implementaion;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.libraryapp.dao.IBorrowDAO;
import pl.edu.wszib.libraryapp.model.Book;
import pl.edu.wszib.libraryapp.model.Borrow;
import pl.edu.wszib.libraryapp.services.IBookService;
import pl.edu.wszib.libraryapp.services.IBorrowService;
import pl.edu.wszib.libraryapp.session.SessionObject;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowService implements IBorrowService {

    @Autowired
    IBorrowDAO borrowDAO;
    @Autowired
    IBookService bookService;
    @Resource
    SessionObject sessionObj;
    @Override
    public void persist(int bookId) {
        Book book = this.bookService.getById(bookId).get();
        book.setRentStatus(false);
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setUser(this.sessionObj.getLoggedUser());
        borrow.setStartDate(LocalDate.now());
        borrow.setReturnDate(LocalDate.now().plusDays(20));
        borrowDAO.persist(borrow);
    }

    @Override
    public List<Borrow> getAllById(int id) {
        return this.borrowDAO.getAllById(id);
    }

    @Override
    public List<Borrow> getAll() {
        return this.borrowDAO.getAll();
    }

    @Override
    public List<Borrow> getAllBorrowed() {
        return this.borrowDAO.getAllBorrowed();
    }

    @Override
    public List<Borrow> getAllOverdue() {
        return this.borrowDAO.getAllOverdue();
    }

    @Override
    public void bookReturn(int reservationId) {
        Borrow reservation = this.borrowDAO.getById(reservationId).get();
        Book book = reservation.getBook();
        reservation.setReturnDate(LocalDate.now());
        book.setRentStatus(true);
        this.borrowDAO.bookReturn(reservation);
        this.bookService.update(book);
    }
}
