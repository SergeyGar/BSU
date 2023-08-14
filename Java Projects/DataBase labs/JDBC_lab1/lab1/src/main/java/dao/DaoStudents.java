package dao;


import domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoStudents {
    private DataBaseConnection dbConnection = new DataBaseConnection();
    private Connection connection = dbConnection.getConnection();
    public int getNextStudentId() {
        int maxId = 0;
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT MAX(id) FROM public.\"Students\"";
            ResultSet resultSet = statement.executeQuery(SQL);
            if (resultSet.next()) {
                maxId = resultSet.getInt("max");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return maxId + 1;
    }

    public void addStudent(Student student){
        try {
            String SQL = "INSERT INTO public.\"Students\" (id, student_name, group_number) Values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getStudent_name());
            preparedStatement.setInt(3, student.getGroup_number());
            preparedStatement.executeUpdate();

            String SQL2 = "INSERT INTO public.\"Results\" (student_id, subject_id, mark) Values(?,?,?)";
            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL2);
            for (Map.Entry<Integer, Integer> pair: student.getMarks().entrySet()) {
                preparedStatement2.setInt(1, student.getId());
                preparedStatement2.setInt(2, pair.getKey());
                preparedStatement2.setInt(3, pair.getValue());
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteStudent(Student student){
        try {
            String SQL = "DELETE FROM public.\"Students\" WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.executeUpdate();

            String SQL2 = "DELETE FROM public.\"Results\" WHERE student_id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL2);
            preparedStatement2.setInt(1, student.getId());
            preparedStatement2.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void editStudent(Student student) {
        try {
            String SQL = "UPDATE public.\"Students\" SET group_number = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, student.getGroup_number());
            preparedStatement.setInt(2, student.getId());
            preparedStatement.executeUpdate();

            String SQL2 = "UPDATE public.\"Results\" SET mark = ? WHERE student_id = ? AND subject_id = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL2);
            for (Map.Entry<Integer, Integer> pair: student.getMarks().entrySet()) {
                preparedStatement2.setInt(1, pair.getValue());
                preparedStatement2.setInt(2, student.getId());
                preparedStatement2.setInt(3, pair.getKey());
                preparedStatement2.executeUpdate();
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> answer = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM public.\"Students\" ORDER BY id";
            ResultSet resultSet = statement.executeQuery(SQL);

            String SQL2 = "SELECT * FROM public.\"Results\" WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL2);

            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setGroup_number(resultSet.getInt("group_number"));
                student.setStudent_name(resultSet.getString("student_name"));

                Map<Integer, Integer> marks = new HashMap<>();
                preparedStatement.setInt(1, student.getId());
                ResultSet resultSet2 = preparedStatement.executeQuery();
                while(resultSet2.next()) {
                    marks.put(resultSet2.getInt("subject_id"), resultSet2.getInt("mark"));
                }
                student.setMarks(marks);
                answer.add(student);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return answer;
    }

    public List<Student> getDebtors() {
        List<Student> answer = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT DISTINCT id, student_name, group_number" +
                    " FROM public.\"Students\", public.\"Results\"" +
                    " WHERE id = student_id AND mark < 4" +
                    " ORDER BY group_number, student_name";
            ResultSet resultSet = statement.executeQuery(SQL);

            String SQL2 = "SELECT * FROM public.\"Results\" WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL2);

            while(resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setGroup_number(resultSet.getInt("group_number"));
                student.setStudent_name(resultSet.getString("student_name"));

                Map<Integer, Integer> marks = new HashMap<>();
                preparedStatement.setInt(1, student.getId());
                ResultSet resultSet2 = preparedStatement.executeQuery();
                while(resultSet2.next()) {
                    marks.put(resultSet2.getInt("subject_id"), resultSet2.getInt("mark"));
                }
                student.setMarks(marks);
                answer.add(student);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return answer;
    }
}