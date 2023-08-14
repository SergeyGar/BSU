package realization.impl;

import dao.DaoStudents;
import domain.Student;
import realization.Students;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

/**
 * Implements the remote interface for managing students.
 * Extends {@link UnicastRemoteObject}.
 * Implements {@link Students}.
 * @author Siarhei Harashchenka
 */
public class StudentsImpl extends UnicastRemoteObject implements Students, Serializable {

    /**
     * Field which represents the data access object for students
     */
    private final DaoStudents daoStudents;

    /**
     * Constructs a StudentsImpl object.
     *
     * @param daoStudents the data access object for students
     * @throws SQLException    if a database access error occurs
     * @throws RemoteException if a remote exception occurs
     */
    public StudentsImpl(DaoStudents daoStudents) throws SQLException, RemoteException {
        super();
        this.daoStudents = daoStudents;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNextStudentId() throws RemoteException {
        return daoStudents.getNextStudentId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addStudent(Student student) throws RemoteException {
        daoStudents.addStudent(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteStudent(int id) throws RemoteException {
        daoStudents.deleteStudent(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void editStudent(Student student) throws RemoteException {
        daoStudents.editStudent(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> getAllStudents() throws RemoteException {
        return daoStudents.getAllStudents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> getDebtors() throws RemoteException {
        return daoStudents.getDebtors();
    }
}
