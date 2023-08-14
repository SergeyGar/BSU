package dao.impl;

import dao.DaoSubjects;
import domain.Subject;
import lombok.RequiredArgsConstructor;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DaoSubjectsImpl implements DaoSubjects, Serializable {

    File pathSubjects = new File("C:\\download\\JavaProjects\\RMI_StAX__lab\\Server\\base\\subjects.xml");

    public DaoSubjectsImpl(){
        if (!pathSubjects.exists()) {
            try {
                pathSubjects.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
//        List<Subject> answer = new ArrayList<>();
//        try {
//            Connection connection = dataSource.getConnection("postgres", "hotstop9331");
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM public.\"Subjects\" ORDER BY id";
//            ResultSet resultSet = statement.executeQuery(SQL);
//            while(resultSet.next()) {
//                Subject subject = new Subject();
//                subject.setId(resultSet.getInt("id"));
//                subject.setName(resultSet.getString("subject_name"));
//                answer.add(subject);
//            }
//        } catch (SQLException throwable){
//            throwable.printStackTrace();
//        }
//
//        return answer;
        List<Subject> subjects = new ArrayList<>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(pathSubjects.getAbsolutePath()));

            int eventType;
            Subject currentSubject = null;

            while (reader.hasNext()) {
                eventType = reader.next();

                switch (eventType) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if (elementName.equals("subject")) {
                            currentSubject = new Subject();
                        } else if (currentSubject != null) {
                            switch (elementName) {
                                case "id":
                                    currentSubject.setId(Integer.parseInt(reader.getElementText()));
                                    break;
                                case "name":
                                    currentSubject.setName(reader.getElementText());
                                    break;
                                case "mark":
                                    currentSubject.setMark(Integer.parseInt(reader.getElementText()));
                                    break;
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if (reader.getLocalName().equals("subject") && currentSubject != null) {
                            subjects.add(currentSubject);
                            currentSubject = null;
                        }
                        break;
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;

    }

    @Override
    public Subject getById(int id) {
//        Subject subject = new Subject();
//        try {
//            Connection connection = dataSource.getConnection("postgres", "hotstop9331");
//            String SQL = "SELECT * FROM public.\"Subjects\" WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                subject.setId(resultSet.getInt("id"));
//                subject.setName(resultSet.getString("subject_name"));
//            }
//        } catch (SQLException throwable){
//            throwable.printStackTrace();
//        }
//
//        return subject;
        Subject subject = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(pathSubjects.getAbsolutePath()));

            int eventType;
            boolean neededId = false;

            while (reader.hasNext()) {
                eventType = reader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    String elementName = reader.getLocalName();
                    switch (elementName){
                        case "subject" -> subject = new Subject();
                        case "id" -> {
                            int subject_id = Integer.parseInt(reader.getElementText());
                            subject.setId(subject_id);
                            neededId = subject_id == id;
                        }
                        case "name" -> {
                            String name = reader.getElementText();
                            subject.setName(name);
                        }
                        case "mark" -> {
                            int mark = Integer.parseInt(reader.getElementText());
                            subject.setMark(mark);
                        }
                    }
                }
                else if(eventType == XMLStreamConstants.END_ELEMENT){
                    String elementName = reader.getLocalName();
                    if (elementName.equals("subject") && neededId) {
                        break;
                    }
                    else if(elementName.equals("subjects")){
                        System.out.println("No subject with " + id + " found");
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subject;
    }
}
