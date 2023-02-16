package ua.com.alevel.persistence.config;

import java.sql.Connection;
import java.sql.Statement;

public interface JdbcService {

    Connection getConnection();
    Statement getStatement();
}
