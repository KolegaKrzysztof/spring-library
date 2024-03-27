package pl.edu.wszib.libraryapp.services.implementaion;

import jakarta.annotation.Resource;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.libraryapp.dao.IUserDAO;
import pl.edu.wszib.libraryapp.model.User;
import pl.edu.wszib.libraryapp.services.IAuthenticationService;
import pl.edu.wszib.libraryapp.session.SessionObject;

import java.util.Optional;


@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDAO;
    @Resource
    SessionObject sessionObject;

    @Override
    public void login(String login, String password) {
        Optional<User> userFromDB = this.userDAO.getByLogin(login);
        if (userFromDB.isPresent() &&
                userFromDB.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setLoggedUser(userFromDB.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setLoggedUser(null);
    }
}