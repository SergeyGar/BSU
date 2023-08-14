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

public class EditStudentWindowController {
    private final Student currentStudent;
    private final DaoStudents daoStudents;
    private final DaoSubjects daoSubjects;
    public EditStudentWindowController(Student currentStudent) throws NotBoundException, RemoteException {
        this.currentStudent = currentStudent;
        final Registry registry = LocateRegistry.getRegistry(9331);

        daoStudents = (DaoStudents) registry.lookup("Students");

        daoSubjects = (DaoSubjects) registry.lookup("Subjects");

    }
    @FXML
    private TextField textField_student_name;
    @FXML
    private ComboBox<Integer> comboBox_groupNumber;
    @FXML
    private TableView<Subject> subjectsTable;
    @FXML
    private Button buttonEdit;

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
            if(event.getNewValue() != null) {
                subject.setMark(event.getNewValue());
            }
            subjectsTable.requestFocus();
        });

        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Integer> mark : currentStudent.getMarks().entrySet()) {
            Subject subject = daoSubjects.getById(mark.getKey());
            subject.setMark(mark.getValue());
            subjects.add(subject);
        }
        subjectsTable.setItems(subjects);


        textField_student_name.setText(currentStudent.getStudent_name());


        ObservableList<Integer> groupOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        comboBox_groupNumber.setItems(groupOptions);
        comboBox_groupNumber.setValue(currentStudent.getGroup_number());


        buttonEdit.setOnAction(ActionEvent -> {
            currentStudent.setGroup_number(comboBox_groupNumber.getValue());
            ObservableList<Subject> activeSubjects = subjectsTable.getItems();
            Map<Integer, Integer> marks = new HashMap<>();
            for (Subject subject : activeSubjects) {
                marks.put(subject.getId(), subject.getMark());
            }
            currentStudent.setMarks(marks);
            daoStudents.editStudent(currentStudent);
            Stage stage = (Stage) buttonEdit.getScene().getWindow();
            stage.close();
        });
    }
}
