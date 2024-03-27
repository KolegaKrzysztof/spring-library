package pl.edu.wszib.libraryapp.services;

import pl.edu.wszib.libraryapp.model.Borrow;

import java.util.List;

public interface IBorrowService {
    void persist(int bookId);
    List<Borrow> getAllById(int id);
    List<Borrow> getAll();
    List<Borrow> getAllLoaned();
    List<Borrow> getAllOverdue();
    void bookReturn(int loanId);
}
