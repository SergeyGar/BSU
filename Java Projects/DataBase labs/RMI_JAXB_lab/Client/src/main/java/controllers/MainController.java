package controllers;

import com.example.client.MainApplication;
import domain.Student;
import domain.Subject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import realization.Students;
import realization.Subjects;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;

public class MainController {
    public boolean allView, debtorsView;

    public static final String UNIQUE_BINDING_NAME_FOR_STUDENTS = "Students";

    public static final String UNIQUE_BINDING_NAME_FOR_SUBJECTS = "Subjects";

    @FXML
    public Button buttonAdd;

    @FXML
    public Button buttonEdit;
    private final Students students;
    private final Subjects subjects;

    @FXML
    private Button buttonAll;
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
    private void initialize() throws RemoteException {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_groupNumber.setCellValueFactory(new PropertyValueFactory<>("group"));
        col_student_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        textArea_marks.setEditable(false);

        buttonDebtors.setOnAction(ActionEvent -> {
            try {
                studentsTable.setItems(FXCollections.observableList(students.getDebtors()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            allView = false;
            debtorsView = true;
        });

        buttonDel.setOnAction(ActionEvent -> {
            Student student = studentsTable.getSelectionModel().getSelectedItem();
            if (student != null) {
                try {
                    students.deleteStudent(student);

                    updateOnDel();
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        buttonAll.setOnAction(ActionEvent -> {
            try {
                updateTable();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });

        studentsTable.setOnMouseClicked(mouseEvent -> {
            Student student = studentsTable.getSelectionModel().getSelectedItem();
            if(student != null) {
                try {
                    textArea_marks.setText(studentMarks(student));
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        updateTable();
    }

    public MainController() throws NotBoundException, RemoteException {
        final Registry registry = LocateRegistry.getRegistry(9331);

        students = (Students) registry.lookup(UNIQUE_BINDING_NAME_FOR_STUDENTS);

        subjects = (Subjects) registry.lookup(UNIQUE_BINDING_NAME_FOR_SUBJECTS);

        int x = 0;
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
    private void openEdit_student_window(ActionEvent actionEvent) throws IOException, NotBoundException {
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
    private String studentMarks(Student student) throws RemoteException {
        StringBuilder stringBuilder = new StringBuilder();
        Map<Integer, Integer> grades = student.getMarks();
        for (Map.Entry<Integer, Integer> pair : grades.entrySet()) {
            Subject subject = subjects.getById(pair.getKey());
            stringBuilder.append(subject.getName()).append(": ");
            stringBuilder.append(pair.getValue());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
    private void updateOnDel() throws RemoteException {
        if (allView)
            studentsTable.setItems(FXCollections.observableList(students.getAllStudents()));
        if (debtorsView)
            studentsTable.setItems(FXCollections.observableList(students.getDebtors()));
    }

    private void updateTable() throws RemoteException {
        studentsTable.setItems(FXCollections.observableList(students.getAllStudents()));
        allView = true;
        debtorsView = false;
    }
}
