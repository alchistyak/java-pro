package tech.inno.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> getById(Long id) throws SQLException;
    List<T> getAll() throws SQLException;
    void insert(T t) throws SQLException;
    void update(T t, String[] params);
    void delete(T t) throws SQLException;
}
