package dao.impl;

import dao.DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionImpl implements DataBaseConnection {
    static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    static final String USERNAME = "postgres";

    static final String PASSWORD = "hotstop9331";

    static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
