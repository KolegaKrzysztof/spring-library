package pl.edu.wszib.libraryapp.dao.implementation.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.libraryapp.dao.IBorrowDAO;
import pl.edu.wszib.libraryapp.model.Borrow;

import java.util.List;
import java.util.Optional;

@Repository
public class BorrowDAO implements IBorrowDAO {

    private final String LOAN_BY_ID = "FROM pl.edu.wszib.libraryapp.model.Borrow WHERE id = :id";
    private final String ALL_LOANS = "FROM pl.edu.wszib.libraryapp.model.Borrow";
    private final String ALL_LOANS_BY_USER_ID =
            "FROM pl.edu.wszib.libraryapp.model.Borrow WHERE user.id = :user_id";
    private final String ALL_LOANED_BOOKS =
            "FROM pl.edu.wszib.libraryapp.model.Borrow WHERE returnDate IS NULL";
    private final String ALL_OVERDUE_LOANS =
            "FROM pl.edu.wszib.libraryapp.model.Borrow " +
                    "WHERE (returnDate IS NULL AND DATEDIFF(endDate,current_date ) <= -1)" +
                    "OR (returnDate IS NOT NULL AND DATEDIFF(endDate, returnDate ) <= -1)";

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Borrow> getAll() {
        Session session = this.sessionFactory.openSession();
        Query<Borrow> query = session.createQuery(ALL_LOANS, Borrow.class);
        List<Borrow> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Borrow> getAllById(int userId) {
        Session session = this.sessionFactory.openSession();
        Query<Borrow> query = session.createQuery(ALL_LOANS_BY_USER_ID,Borrow.class);
        query.setParameter("user_id", userId);
        List<Borrow> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Borrow> getAllLoaned() {
        Session session = this.sessionFactory.openSession();
        Query<Borrow> query = session.createQuery(ALL_LOANED_BOOKS,Borrow.class);
        List<Borrow> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public List<Borrow> getAllOverdue() {
        Session session = this.sessionFactory.openSession();
        Query<Borrow> query = session.createQuery(ALL_OVERDUE_LOANS,Borrow.class);
        List<Borrow> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Borrow> getById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Borrow> query = session.createQuery(LOAN_BY_ID,Borrow.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }finally {
            session.close();
        }
    }

    @Override
    public void persist(Borrow borrow) {
        Session session = this.sessionFactory.openSession();
        borrow.setBook(session.merge(borrow.getBook()));
        try {
            session.beginTransaction();
            session.persist(borrow);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void bookReturn(Borrow borrow) {
        Session session = this.sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.merge(borrow);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}