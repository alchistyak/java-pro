package tech.inno.dao;

import org.springframework.stereotype.Repository;
import tech.inno.datasource.DataSourceInterface;
import tech.inno.data.Product;
import tech.inno.data.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    DataSourceInterface dataSourceInterface;
    UserRepository userRepository;

    public ProductRepository(DataSourceInterface dataSourceInterface, UserRepository userRepository) {
        this.dataSourceInterface = dataSourceInterface;
        this.userRepository = userRepository;
    }

    public List<Product> getProductsByUserId(User user) throws SQLException {
        String query = "select * from products where userid = ?";
        List<Product> products = new ArrayList<>();
        try (
                Connection connection = dataSourceInterface.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, user.getId());
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    products.add(new Product(user, resultSet.getLong("id"), resultSet.getString("account"), resultSet.getLong("balance"), resultSet.getString("type")));
                }
            }
        }
        return products;
    }

    public Product getProductById(Long id) throws SQLException {
        String query = "select * from products where id = ?";
        Product product = null;
        try (
                Connection connection = dataSourceInterface.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setLong(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    User user = userRepository.getById(resultSet.getLong("userid")).get();
                    product = new Product(user, resultSet.getLong("id"), resultSet.getString("account"), resultSet.getLong("balance"), resultSet.getString("type"));
                }
            }
        }
        return product;
    }

    public Long save(Product product) throws SQLException {
        String query = "insert into products (account, balance, type, userid) values (?, ?, ?, ?) returning id";
        try (
                Connection connection = dataSourceInterface.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, product.getAccount());
            preparedStatement.setLong(2, product.getBalance());
            preparedStatement.setString(3, product.getType());
            preparedStatement.setLong(4, product.getUser().getId());
            ResultSet resultSet = preparedStatement.executeQuery();//.executeUpdate();
            resultSet.next();
            return resultSet.getLong("id");
        }
    }
}
