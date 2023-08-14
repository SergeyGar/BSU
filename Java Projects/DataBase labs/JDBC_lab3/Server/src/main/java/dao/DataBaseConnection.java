package dao;

import java.rmi.Remote;
import java.sql.Connection;

public interface DataBaseConnection extends Remote {
    Connection getConnection();
}
