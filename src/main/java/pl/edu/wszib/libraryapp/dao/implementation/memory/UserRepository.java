package pl.edu.wszib.libraryapp.dao.implementation.memory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.wszib.libraryapp.dao.IUserDAO;
import pl.edu.wszib.libraryapp.model.User;
import pl.edu.wszib.libraryapp.model.User.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository implements IUserDAO {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "admin", DigestUtils.md5Hex("admin"), Role.ADMIN));
        users.add(new User(2, "user", DigestUtils.md5Hex("user"), Role.USER));
    }

    @Override
    public Optional<User> getById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void persist(final User user) {
        this.users.stream()
                .map(User::getId)
                .max(Integer::compare)
                .ifPresentOrElse(
                        i -> user.setId(i + 1),
                        () -> user.setId(1)
                );
        this.users.add(user);
    }
}
