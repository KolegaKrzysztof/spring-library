package pl.edu.wszib.libraryapp.services;

import pl.edu.wszib.libraryapp.model.User;

public interface IUserService {
    void persist(User user);
    boolean userExist(String login);
    void setInDb();
}
