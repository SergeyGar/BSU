package realization.impl;

import dao.DaoSubjects;
import domain.Subject;
import realization.Subjects;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

/**
 * Implements the remote interface for managing subjects.
 * Extends {@link UnicastRemoteObject}
 * Implements {@link Subjects}
 * @author Siarhei Harashchenka
 */
public class SubjectsImpl extends UnicastRemoteObject implements Subjects, Serializable {

    /**
     * Field which represents the data access object for subjects
     */
    private final DaoSubjects daoSubjects;

    /**
     * Constructs a SubjectsImpl object.
     *
     * @param daoSubjects the data access object for subjects
     * @throws SQLException    if a database access error occurs
     * @throws RemoteException if a remote exception occurs
     */
    public SubjectsImpl(DaoSubjects daoSubjects) throws SQLException, RemoteException {
        super();
        this.daoSubjects = daoSubjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subject> getAllSubjects() throws RemoteException {
        return daoSubjects.getAllSubjects();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Subject getById(int id) throws RemoteException {
        return daoSubjects.getById(id);
    }
}
