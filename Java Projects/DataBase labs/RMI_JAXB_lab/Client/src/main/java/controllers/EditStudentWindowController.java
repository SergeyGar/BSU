package controllers;

import domain.Student;
import domain.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import realization.Students;
import realization.Subjects;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

public class EditStudentWindowController {
    private final Student currentStudent;
    private final Students students;
    private final Subjects subjects;

    public static final String UNIQUE_BINDING_NAME_FOR_STUDENTS = "Students";

    public static final String UNIQUE_BINDING_NAME_FOR_SUBJECTS = "Subjects";

    public EditStudentWindowController(Student currentStudent) throws NotBoundException, RemoteException {
        this.currentStudent = currentStudent;
        final Registry registry = LocateRegistry.getRegistry(9331);

        students = (Students) registry.lookup(UNIQUE_BINDING_NAME_FOR_STUDENTS);

        subjects = (Subjects) registry.lookup(UNIQUE_BINDING_NAME_FOR_SUBJECTS);

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
    private void initialize() throws RemoteException {
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
            Subject subject = this.subjects.getById(mark.getKey());
            subject.setMark(mark.getValue());
            subjects.add(subject);
        }
        subjectsTable.setItems(subjects);


        textField_student_name.setText(currentStudent.getName());


        ObservableList<Integer> groupOptions = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        comboBox_groupNumber.setItems(groupOptions);
        comboBox_groupNumber.setValue(currentStudent.getGroup());


        buttonEdit.setOnAction(ActionEvent -> {
            currentStudent.setGroup(comboBox_groupNumber.getValue());
            ObservableList<Subject> activeSubjects = subjectsTable.getItems();
            Map<Integer, Integer> marks = new HashMap<>();
            for (Subject subject : activeSubjects) {
                marks.put(subject.getId(), subject.getMark());
            }
            currentStudent.setMarks(marks);
            try {
                students.editStudent(currentStudent);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) buttonEdit.getScene().getWindow();
            stage.close();
        });
    }
}
