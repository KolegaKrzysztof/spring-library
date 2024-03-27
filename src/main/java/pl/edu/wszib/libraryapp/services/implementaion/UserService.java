package pl.edu.wszib.libraryapp.services.implementaion;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.libraryapp.dao.IUserDAO;
import pl.edu.wszib.libraryapp.model.User;
import pl.edu.wszib.libraryapp.model.User.Role;
import pl.edu.wszib.libraryapp.services.IUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public void persist(User user) {
        this.userDAO.persist(user);
    }

    @Override
    public boolean userExist(String login) {
        return false;
    }

    @Override
    public void setInDb() {
        List<User> users = new ArrayList<>();
        users.add(new User("admin", DigestUtils.md5Hex("admin"), Role.ADMIN));
        users.add(new User("user", DigestUtils.md5Hex("user"), Role.USER));

        for (User user : users) {
            this.userDAO.persist(user);
        }
    }
}