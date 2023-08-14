package dao;

import domain.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Represents a remote interface for accessing subjects data.
 * <p>This interface has class that implements it.</p>
 * {@link dao.impl.DaoSubjectsImpl} which works with data stored in <strong>PostgreSQL</strong>
 * @see Remote
 * @see java.sql
 * @author Siarhei Harashchenka
 */
public interface DaoSubjects extends Remote {

    /**
     * Retrieves a list of all subjects from the data source.
     *
     * @return {@link List} of {@link Subject}
     * @throws RemoteException if a remote exception occurs
     */
    List<Subject> getAllSubjects() throws RemoteException;

    /**
     * Retrieves a subject by its ID from the data source.
     *
     * @param id the ID of the subject to retrieve
     * @return {@link Subject}
     * @throws RemoteException if a remote exception occurs
     */
    Subject getById(int id) throws RemoteException;
}
