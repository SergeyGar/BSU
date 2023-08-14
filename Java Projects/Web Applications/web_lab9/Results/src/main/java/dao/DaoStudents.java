package dao;

import domain.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Represents a remote interface for accessing student data.
 * <p>This interface has class that implements it.</p>
 * {@link dao.impl.DaoStudentsImpl} which works with data stored in <strong>PostgreSQL</strong>
 * @see Remote
 * @see java.sql
 * @author Siarhei Harashchenka
 */
public interface DaoStudents extends Remote {

    /**
     * Retrieves the next available student ID.
     *
     * @return int
     * @throws RemoteException if a remote exception occurs
     * @see Student
     */
    int getNextStudentId() throws RemoteException;

    /**
     * Adds a new student to the data source.
     *
     * @param student the student to be added
     * @throws RemoteException if a remote exception occurs
     * @see Student
     */
    void addStudent(Student student) throws RemoteException;

    void deleteStudent(int id) throws RemoteException;

    /**
     * Edits the details of a student in the data source.
     *
     * @param student the student to be edited
     * @throws RemoteException if a remote exception occurs
     * @see Student
     */
    void editStudent(Student student) throws RemoteException;

    /**
     * Retrieves a list of all students from the data source.
     *
     * @return {@link List} of {@link Student}
     * @throws RemoteException if a remote exception occurs
     */
    List<Student> getAllStudents() throws RemoteException;

    /**
     * Retrieves a list of students who have debts.
     *
     * @return {@link List} of {@link Student}
     * @throws RemoteException if a remote exception occurs
     */
    List<Student> getDebtors() throws RemoteException;
}
