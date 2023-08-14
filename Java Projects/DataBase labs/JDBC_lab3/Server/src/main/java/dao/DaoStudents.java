package dao;

import domain.Student;

import java.rmi.Remote;
import java.util.List;

public interface DaoStudents extends Remote {

    int getNextStudentId();

    void addStudent(Student student);

    void deleteStudent(Student student);

    void editStudent(Student student);

    List<Student> getAllStudents();

    List<Student> getDebtors();
}
