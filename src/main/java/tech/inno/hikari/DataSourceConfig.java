package tech.inno.hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfig {
    private static HikariConfig hikariConfig =  new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        hikariConfig.setJdbcUrl("jdbc:postgresql://tst-zabbix005:5432/feoktistov_v");
        hikariConfig.setUsername("orts");
        hikariConfig.setPassword("orts");
        dataSource = new HikariDataSource(hikariConfig);
    }
}
