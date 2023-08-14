package realization.impl;

import dao.DaoStudents;
import domain.Student;
import realization.Students;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class StudentsImpl extends UnicastRemoteObject implements Students, Serializable {

    public StudentsImpl(DaoStudents daoStudents) throws SQLException, RemoteException{
        super();
        this.daoStudents = daoStudents;
    }
    private final DaoStudents daoStudents;

    @Override
    public int getNextStudentId() throws RemoteException {return daoStudents.getNextStudentId();}

    @Override
    public void addStudent(Student student) throws RemoteException {daoStudents.addStudent(student);}

    @Override
    public void deleteStudent(Student student) throws RemoteException {daoStudents.deleteStudent(student);}

    @Override
    public void editStudent(Student student) throws RemoteException {daoStudents.editStudent(student);}

    @Override
    public List<Student> getAllStudents() throws RemoteException {return daoStudents.getAllStudents();}

    @Override
    public List<Student> getDebtors() throws RemoteException {return daoStudents.getDebtors();}
}
