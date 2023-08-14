package dao;

import domain.Subject;

import java.rmi.Remote;
import java.util.List;

public interface DaoSubjects extends Remote {

    List<Subject> getAllSubjects();

    Subject getById(int id);
}
