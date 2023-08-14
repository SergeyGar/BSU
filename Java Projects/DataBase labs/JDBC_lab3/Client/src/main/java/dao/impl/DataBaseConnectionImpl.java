package dao.impl;

import dao.DataBaseConnection;

import java.sql.Connection;

public class DataBaseConnectionImpl implements DataBaseConnection {
    private static Connection connection;

//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//
//        if (connection != null) {
//            System.out.println("You successfully connected to database");
//        } else {
//            System.out.println("Failed to make connection to database");
//        }
//    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
