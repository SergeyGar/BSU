package realization.impl;

import dao.DaoSubjects;
import domain.Subject;
import realization.Subjects;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class SubjectsImpl extends UnicastRemoteObject implements Subjects, Serializable {

    public SubjectsImpl(DaoSubjects daoSubjects) throws SQLException, RemoteException{
        super();
        this.daoSubjects = daoSubjects;
    }

    private final DaoSubjects daoSubjects;

    @Override
    public List<Subject> getAllSubjects() throws RemoteException {return daoSubjects.getAllSubjects();}

    @Override
    public Subject getById(int id) throws RemoteException {return daoSubjects.getById(id);}
}
