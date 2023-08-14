module com.example.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;
    requires lombok;
    requires java.sql;


    opens com.example.client to javafx.fxml;
    opens controllers to javafx.fxml;
    opens domain to javafx.base;
    exports com.example.client;
    exports controllers;
}