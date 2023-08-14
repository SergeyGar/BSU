package realization;

import domain.Subject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Subjects extends Remote {
    List<Subject> getAllSubjects() throws RemoteException;

    Subject getById(int id) throws RemoteException;

}
