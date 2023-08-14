module com.example.lab1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.lab1 to javafx.fxml;
    exports com.example.lab1;
    exports controllers;
    opens controllers to javafx.fxml;
    opens domain;
}