package dao;

import domain.Student;

import java.util.List;

public interface DaoStudents {

    int getNextStudentId();

    void addStudent(Student student);

    void deleteStudent(Student student);

    void editStudent(Student student);

    List<Student> getAllStudents();

    List<Student> getDebtors();
}
