package realization;

import domain.Student;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Represents a remote interface for managing students.
 * @see Remote
 * @author Siarhei Harashchenka
 */
public interface Students extends Remote {

    /**
     * Retrieves the next available ID for a new student.
     *
     * @return the next student ID
     * @throws RemoteException if a remote exception occurs
     */
    int getNextStudentId() throws RemoteException;

    /**
     * Adds a new student to the system.
     *
     * @param student the student to add
     * @throws RemoteException if a remote exception occurs
     */
    void addStudent(Student student) throws RemoteException;

    /**
     * Deletes a student from the system.
     *
     * @param student the student to delete
     * @throws RemoteException if a remote exception occurs
     */
    void deleteStudent(int id) throws RemoteException;

    /**
     * Edits the information of an existing student in the system.
     *
     * @param student the student to edit
     * @throws RemoteException if a remote exception occurs
     */
    void editStudent(Student student) throws RemoteException;

    /**
     * Retrieves a list of all students in the system.
     *
     * @return a list of all students
     * @throws RemoteException if a remote exception occurs
     */
    List<Student> getAllStudents() throws RemoteException;

    /**
     * Retrieves a list of students who have outstanding debts.
     *
     * @return a list of debtor students
     * @throws RemoteException if a remote exception occurs
     */
    List<Student> getDebtors() throws RemoteException;
}
