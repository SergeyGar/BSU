package server;

import dao.impl.DaoStudentsImpl;
import dao.impl.DaoSubjectsImpl;
import org.postgresql.ds.PGSimpleDataSource;
import realization.Students;
import realization.Subjects;
import realization.impl.StudentsImpl;
import realization.impl.SubjectsImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class ServerMain {

    public static final String UNIQUE_BINDING_NAME_FOR_STUDENTS = "Students";

    public static final String UNIQUE_BINDING_NAME_FOR_SUBJECTS = "Subjects";

    public static void main(String[] args) throws RemoteException, InterruptedException, SQLException {

        final Students students = new StudentsImpl(new DaoStudentsImpl());
        final Subjects subjects = new SubjectsImpl(new DaoSubjectsImpl());

        Registry registry = LocateRegistry.createRegistry(9331);

        registry.rebind(UNIQUE_BINDING_NAME_FOR_STUDENTS, students);
        registry.rebind(UNIQUE_BINDING_NAME_FOR_SUBJECTS, subjects);

        System.out.println("Server is active!");

        while (true) {
            Thread.sleep(Integer.MAX_VALUE);
        }
    }
}