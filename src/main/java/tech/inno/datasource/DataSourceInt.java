package tech.inno.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceInt {
    Connection getConnection() throws SQLException;
    void closeConnection();
}
