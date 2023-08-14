package realization;

import domain.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Represents a remote interface for managing subjects.
 *
 * @see Remote
 * @author Siarhei Harashchenka
 */
public interface Subjects extends Remote {

    /**
     * Retrieves a list of all subjects.
     *
     * @return {@link List} of {@link Subject}
     * @throws RemoteException if a remote exception occurs
     */
    List<Subject> getAllSubjects() throws RemoteException;

    /**
     * Retrieves a subject by its ID.
     *
     * @param id the ID of the subject
     * @return {@link Subject}
     * @throws RemoteException if a remote exception occurs
     */
    Subject getById(int id) throws RemoteException;
}
