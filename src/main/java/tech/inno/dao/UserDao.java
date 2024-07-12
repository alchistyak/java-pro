package tech.inno.dao;

import org.springframework.stereotype.Repository;
import tech.inno.datasource.DataSource;
import tech.inno.datasource.DataSourceInt;
import tech.inno.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao implements Dao<User> {
    DataSourceInt dataSourceInt;

    public UserDao(DataSourceInt dataSourceInt) {
        this.dataSourceInt = dataSourceInt;
    }

    @Override
    public Optional<User> getById(Long id) throws SQLException {
        String query = "select * from users where id = ?";
        User user = null;
        try (
                Connection connection = dataSourceInt.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    user = new User(resultSet.getLong("id"), resultSet.getString("username"));
                }
            }
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAll() throws SQLException {
        String query = "select * from users";
        List<User> users = null;
        try (
                Connection connection = dataSourceInt.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = new User(resultSet.getLong("id"), resultSet.getString("username"));
                users.add(user);
            }
        }
        return users;
    }

    @Override
    public void insert(User user) throws SQLException {
        String query = "insert into users (id, username) values (?, ?)";
        try (
                Connection connection = dataSourceInt.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) throws SQLException {
        String query = "delete from users where id = ?";
        try (
                Connection connection = dataSourceInt.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        }
    }
}
