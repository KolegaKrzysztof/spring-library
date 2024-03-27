package pl.edu.wszib.libraryapp.dao;

import pl.edu.wszib.libraryapp.model.Borrow;

import java.util.List;
import java.util.Optional;

public interface IBorrowDAO {
    List<Borrow> getAll();
    List<Borrow> getAllById(int id);
    List<Borrow> getAllLoaned();
    List<Borrow> getAllOverdue();
    Optional<Borrow> getById(int id);
    void persist(Borrow borrow);
    void bookReturn(Borrow borrow);
}
