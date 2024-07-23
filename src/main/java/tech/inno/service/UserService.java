package tech.inno.service;

import org.springframework.stereotype.Service;
import tech.inno.dao.Dao;
import tech.inno.dto.UserDto;
import tech.inno.data.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    Dao<User> userDao;

    public UserService(Dao<User> userDao) {
        this.userDao = userDao;
    }

    public List<User> fetchAllUsers() throws SQLException {
        return userDao.getAll();
    }

    public UserDto fetchUserById(Long id) throws SQLException {
        User user = userDao.getById(id).get();
        return new UserDto(user.getId(), user.getUsername());
    }

    public void addUser(User user) throws SQLException {
        userDao.insert(user);
    }

    public void deleteUser(User user) throws SQLException {
        userDao.delete(user);
    }
}
