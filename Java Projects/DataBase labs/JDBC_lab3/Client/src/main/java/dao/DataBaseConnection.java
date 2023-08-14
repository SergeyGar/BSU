package dao;

import java.sql.Connection;

public interface DataBaseConnection {
    static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    static final String USERNAME = "postgres";

    static final String PASSWORD = "hotstop9331";

    Connection getConnection();
}
