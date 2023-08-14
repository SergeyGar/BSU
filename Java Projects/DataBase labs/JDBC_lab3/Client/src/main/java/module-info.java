module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires java.rmi;


    opens com.example.client to javafx.fxml;
    exports com.example.client;
    exports controllers;
    opens controllers to javafx.fxml;
    opens domain;
}