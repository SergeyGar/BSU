package realization;

import domain.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Students extends Remote {
    int getNextStudentId() throws RemoteException;

    void addStudent(Student student) throws RemoteException;

    void deleteStudent(Student student) throws RemoteException;

    void editStudent(Student student) throws RemoteException;

    List<Student> getAllStudents() throws RemoteException;

    List<Student> getDebtors() throws RemoteException;

}
