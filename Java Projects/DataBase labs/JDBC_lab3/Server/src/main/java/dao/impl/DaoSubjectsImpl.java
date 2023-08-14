package dao.impl;

import dao.DaoSubjects;
import domain.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoSubjectsImpl implements DaoSubjects {
    private DataBaseConnectionImpl dbConnection = new DataBaseConnectionImpl();
    private Connection connection = dbConnection.getConnection();

    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> answer = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM public.\"Subjects\" ORDER BY id";
            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("subject_name"));
                answer.add(subject);
            }
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }

        return answer;
    }

    @Override
    public Subject getById(int id) {
        Subject subject = new Subject();
        try {
            String SQL = "SELECT * FROM public.\"Subjects\" WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("subject_name"));
            }
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }

        return subject;
    }
}
