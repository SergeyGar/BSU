package dao;

import domain.Subject;

import java.util.List;

public interface DaoSubjects {

    List<Subject> getAllSubjects();

    Subject getById(int id);
}
