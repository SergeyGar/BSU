package controllers;

import com.example.lab1.MainApplication;
import dao.DaoStudents;
import dao.DaoSubjects;
import domain.Student;
import domain.Subject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class MainController {
    public boolean allView, debtorsView;
    private final DaoStudents daoStudents = new DaoStudents();
    private final DaoSubjects daoSubjects = new DaoSubjects();
    @FXML
    private TextArea textArea_marks;
    @FXML
    private Button buttonDebtors;

    @FXML
    private TableColumn<Student, Integer> col_id;
    @FXML
    private TableColumn<Student, Integer> col_groupNumber;
    @FXML
    private TableColumn<Student, String> col_student_name;
    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private Button buttonDel;

    @FXML
    private void initialize() {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_groupNumber.setCellValueFactory(new PropertyValueFactory<>("group_number"));
        col_student_name.setCellValueFactory(new PropertyValueFactory<>("student_name"));

        textArea_marks.setEditable(false);

        buttonDebtors.setOnAction(ActionEvent -> {
            studentsTable.setItems(FXCollections.observableList(daoStudents.getDebtors()));
            allView = false;
            debtorsView = true;
        });

        buttonDel.setOnAction(ActionEvent -> {
            Student student = studentsTable.getSelectionModel().getSelectedItem();
            if (student != null) {
                daoStudents.deleteStudent(student);
                updateOnDel();
            }
        });

        studentsTable.setOnMouseClicked(mouseEvent -> {
            Student student = studentsTable.getSelectionModel().getSelectedItem();
            if(student != null)
                textArea_marks.setText(studentMarks(student));
        });

        updateTable();
    }
    @FXML
    private void openAdd_student_window(ActionEvent actionEvent) throws IOException {
        Stage addStudentStage = new Stage();
        addStudentStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("add_student_window.fxml"));
        Scene addStudentScene = new Scene(fxmlLoader.load(), 500, 350);
        addStudentStage.setTitle("Add Student");
        addStudentStage.setScene(addStudentScene);
        addStudentStage.showAndWait();

        updateTable();
    }

    @FXML
    private void openEdit_student_window(ActionEvent actionEvent) throws IOException {
        Student studentForEdit = studentsTable.getSelectionModel().getSelectedItem();
        if(studentForEdit != null) {
            Stage addStudentStage = new Stage();
            addStudentStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("edit_student_window.fxml"));
            EditStudentWindowController editStudentWindowController = new EditStudentWindowController(studentForEdit);
            fxmlLoader.setController(editStudentWindowController);
            Scene editStudentScene = new Scene(fxmlLoader.load(), 500, 350);
            addStudentStage.setTitle("Edit Student");
            addStudentStage.setScene(editStudentScene);
            addStudentStage.showAndWait();

            updateTable();
        }
    }
    @FXML
    private String studentMarks(Student student) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, Integer> grades = student.getMarks();
        for (Map.Entry<Integer, Integer> pair : grades.entrySet()) {
            Subject subject = daoSubjects.getById(pair.getKey());
            stringBuilder.append(subject.getName()).append(": ");
            stringBuilder.append(pair.getValue());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
    private void updateOnDel() {
        if (allView)
            studentsTable.setItems(FXCollections.observableList(daoStudents.getAllStudents()));
        if (debtorsView)
            studentsTable.setItems(FXCollections.observableList(daoStudents.getDebtors()));
    }

    private void updateTable(){
        studentsTable.setItems(FXCollections.observableList(daoStudents.getAllStudents()));
        allView = true;
        debtorsView = false;
    }
}