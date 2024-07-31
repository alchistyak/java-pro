package tech.inno.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource implements DataSourceInterface {
    private static HikariConfig hikariConfig =  new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        hikariConfig.setJdbcUrl("jdbc:postgresql://tst-zabbix005:5432/feoktistov_v");
        hikariConfig.setUsername("orts");
        hikariConfig.setPassword("orts");
        dataSource = new HikariDataSource(hikariConfig);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void closeConnection() {
        dataSource.close();
    }
}
