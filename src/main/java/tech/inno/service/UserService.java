package tech.inno.service;

import org.springframework.stereotype.Service;
import tech.inno.dao.Dao;
import tech.inno.user.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    Dao<User> userDao;

    public UserService(Dao<User> userDao) {
        this.userDao = userDao;
    }

    public List<User> fetchAllUsers() throws SQLException {
        return userDao.getAll();
    }

    public Optional<User> fetchUserById(Long id) throws SQLException {
        return userDao.getById(id);
    }

    public void addUser(User user) throws SQLException {
        userDao.insert(user);
    }
    public void deleteUser(User user) throws SQLException {
        userDao.delete(user);
    }
}
