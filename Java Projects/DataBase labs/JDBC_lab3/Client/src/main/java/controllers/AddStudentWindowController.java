package controllers;

import dao.DaoStudents;
import dao.DaoSubjects;
import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;



public class AddStudentWindowController {
    private final DaoStudents daoStudents;
    private final DaoSubjects daoSubjects;
    @FXML
    private Label label_warning;
    @FXML
    private TextField textField_student_name;
    @FXML
    private ComboBox<Integer> comboBox_groupNumber;
    @FXML
    private TableView<Subject> subjectsTable;
    @FXML
    private Button buttonAdd;

    public AddStudentWindowController() throws NotBoundException, RemoteException {
        final Registry registry = LocateRegistry.getRegistry(9331);

        daoStudents = (DaoStudents) registry.lookup("Students");

        daoSubjects = (DaoSubjects) registry.lookup("Subjects");

    }

    @FXML
    private void initialize() {
        TableColumn<Subject, Integer> col_id = new TableColumn<>("Id");
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        subjectsTable.getColumns().add(col_id);

        TableColumn<Subject, String> col_subjectName = new TableColumn<>("Name");
        col_subjectName.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectsTable.getColumns().add(col_subjectName);

        TableColumn<Subject, Integer> col_mark = new TableColumn<>("Mark");
        col_mark.setCellValueFactory(new PropertyValueFactory<>("mark"));
        subjectsTable.getColumns().add(col_mark);
        col_mark.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        col_mark.setOnEditCommit(event -> {
            Subject subject = event.getRowValue();
            subject.setMark(event.getNewValue());
            subjectsTable.requestFocus();
        });

        subjectsTable.setItems(FXCollections.observableList(daoSubjects.getAllSubjects()));


        ObservableList<Integer> groupOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        comboBox_groupNumber.setItems(groupOptions);
        comboBox_groupNumber.setValue(1);


        buttonAdd.setOnAction(ActionEvent -> {
            if(!textField_student_name.getText().isBlank()) {
                int id = daoStudents.getNextStudentId();
                int group = comboBox_groupNumber.getValue();
                String student_name = textField_student_name.getText();
                Map<Integer, Integer> marks = new HashMap<>();
                ObservableList<Subject> activeSubjects = subjectsTable.getItems();
                for (Subject subject : activeSubjects) {
                    if (subject.getMark() != null) {
                        marks.put(subject.getId(), subject.getMark());
                    }
                }
                Student student = new Student(id, group, student_name, marks);
                daoStudents.addStudent(student);
                Stage stage = (Stage) buttonAdd.getScene().getWindow();
                stage.close();
            }
            else{
                label_warning.setText("Enter student name!");
            }
        });
    }
}
