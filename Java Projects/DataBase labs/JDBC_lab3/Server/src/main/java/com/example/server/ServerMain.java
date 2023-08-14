package com.example.server;

import dao.DaoStudents;
import dao.DaoSubjects;
import dao.DataBaseConnection;
import dao.impl.DaoStudentsImpl;
import dao.impl.DaoSubjectsImpl;
import dao.impl.DataBaseConnectionImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

    public static final String UNIQUE_BINDING_NAME_FOR_STUDENTS = "Students";

    public static final String UNIQUE_BINDING_NAME_FOR_SUBJECTS = "Subjects";

    public static final String UNIQUE_BINDING_NAME_FOR_CONNECTION = "Connection";

    public static void main(String[] args) throws RemoteException, InterruptedException {
        DaoStudents daoStudents = new DaoStudentsImpl();
        DaoSubjects daoSubjects = new DaoSubjectsImpl();
        DataBaseConnection dataBaseConnection = new DataBaseConnectionImpl();

        Registry registry = LocateRegistry.createRegistry(9331);

        registry.rebind(UNIQUE_BINDING_NAME_FOR_STUDENTS, daoStudents);
        registry.rebind(UNIQUE_BINDING_NAME_FOR_SUBJECTS, daoSubjects);
        registry.rebind(UNIQUE_BINDING_NAME_FOR_CONNECTION, dataBaseConnection);


        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}