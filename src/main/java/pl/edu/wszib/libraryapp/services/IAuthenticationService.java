package pl.edu.wszib.libraryapp.services;

public interface IAuthenticationService {
    void login(String login, String password);
    void logout();
}