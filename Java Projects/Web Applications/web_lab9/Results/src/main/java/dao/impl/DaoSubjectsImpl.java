package dao.impl;

import dao.DaoSubjects;
import domain.Subject;
import lombok.RequiredArgsConstructor;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the {@link DaoSubjects} interface that provides access to subject data.
 * <p>Class works with <strong>PostgreSQL</strong> as database.</p>
 * @see dao.DaoStudents
 * @author Siarhei Harashchenka
 */
@RequiredArgsConstructor
public class DaoSubjectsImpl implements DaoSubjects, Serializable {

    /**
     * the field used to access the database
     */
    private final DataSource dataSource;

    public DaoSubjectsImpl(){
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setDatabaseName("postgres");
        dataSource = pgSimpleDataSource;
    }

    public DaoSubjectsImpl(PGSimpleDataSource pgSimpleDataSource){
        dataSource = pgSimpleDataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Subject> getAllSubjects() {
        List<Subject> answer = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection("postgres", "hotstop9331");
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Subject getById(int id) {
        Subject subject = new Subject();
        try {
            Connection connection = dataSource.getConnection("postgres", "hotstop9331");
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
