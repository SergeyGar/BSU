package dao.impl;


import dao.DaoStudents;
import domain.Student;
import domain.Subject;
import lombok.RequiredArgsConstructor;

import javax.xml.stream.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DaoStudentsImpl implements DaoStudents, Serializable {


    File base = new File("C:\\download\\JavaProjects\\RMI_StAX__lab\\Server\\base");

    File pathStudents = new File("C:\\download\\JavaProjects\\RMI_StAX__lab\\Server\\base\\students");

    public DaoStudentsImpl(){
        if (!pathStudents.exists()) {
            pathStudents.mkdirs();
        }
    }


    @Override
    public int getNextStudentId() {
        int maxId = 0;
        File folder = new File(pathStudents.getAbsolutePath());
        File[] files = folder.listFiles();

        if(files != null){
            for(File file: files){
                if(file.isFile() && file.getName().endsWith(".xml")){
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                        XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);

                        while (reader.hasNext()) {
                            int eventType = reader.next();

                            if (eventType == XMLStreamConstants.START_ELEMENT && reader.getLocalName().equals("id")) {
                                String idText = reader.getElementText();
                                int id = Integer.parseInt(idText);

                                if (id > maxId) {
                                    maxId = id;
                                }
                            }
                        }

                        reader.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return maxId + 1;
    }

    @Override
    public void addStudent(Student student){
        XMLOutputFactory studentFactory = XMLOutputFactory.newInstance();
        studentFactory.setProperty("javax.xml.stream.isRepairingNamespaces", true); // Форматирование XML
        try {
            String fileName = student.getStudent_name() + "_" +
                    student.getId() + ".xml";
            String filePath = pathStudents + "/" + fileName;
            OutputStream outputStream = new FileOutputStream(filePath);
            XMLStreamWriter studentWriter = studentFactory.createXMLStreamWriter(outputStream, "UTF-8");

            studentWriter.writeStartDocument("UTF-8", "1.0");
            studentWriter.writeCharacters("\n");
            studentWriter.writeStartElement("student");

            studentWriter.writeCharacters("\n\t");
            studentWriter.writeStartElement("id");
            studentWriter.writeCharacters(String.valueOf(student.getId()));
            studentWriter.writeEndElement();

            studentWriter.writeCharacters("\n\t");
            studentWriter.writeStartElement("student_name");
            studentWriter.writeCharacters(student.getStudent_name());
            studentWriter.writeEndElement();

            studentWriter.writeCharacters("\n\t");
            studentWriter.writeStartElement("group_number");
            studentWriter.writeCharacters(String.valueOf(student.getGroup_number()));
            studentWriter.writeEndElement();

            studentWriter.writeCharacters("\n\t");
            studentWriter.writeStartElement("subjects");

            for (Map.Entry<Integer, Integer> subject: student.getMarks().entrySet()) {
                studentWriter.writeCharacters("\n\t\t");
                studentWriter.writeStartElement("subject");

                studentWriter.writeCharacters("\n\t\t\t");
                studentWriter.writeStartElement("subject_id");
                studentWriter.writeCharacters(String.valueOf(subject.getKey()));
                studentWriter.writeEndElement();

                studentWriter.writeCharacters("\n\t\t\t");
                studentWriter.writeStartElement("mark");
                studentWriter.writeCharacters(String.valueOf(subject.getValue()));
                studentWriter.writeEndElement();

                studentWriter.writeCharacters("\n\t\t");
                studentWriter.writeEndElement();
            }

            studentWriter.writeCharacters("\n\t");
            studentWriter.writeEndElement();

            studentWriter.writeCharacters("\n");
            studentWriter.writeEndElement(); // </student>
            studentWriter.writeEndDocument();
            studentWriter.flush();
            studentWriter.close();

            System.out.println("Student" + student.getStudent_name() + " added!");
        } catch (FileNotFoundException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(Student student){
        File file = new File(pathStudents, student.getStudent_name() + "_" +
                student.getId() + ".xml");
        if(file.exists()){
            file.delete();
        }
    }

    @Override
    public void editStudent(Student student) {


    }

    @Override
    public List<Student> getAllStudents() {
//        List<Student> students = new ArrayList<>();
//        File directory = new File(pathStudents.getAbsolutePath());
//
//        if (directory.exists() && directory.isDirectory()) {
//            File[] files = directory.listFiles();
//            if (files != null) {
//                for (File file : files) {
//                    if (file.isFile() && file.getName().endsWith(".xml")) {
//
//                        XMLInputFactory factory = XMLInputFactory.newInstance();
//
//                        FileInputStream inputStream = null;
//                        try {
//                            inputStream = new FileInputStream(file.getAbsolutePath());
//
//                            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
//
//                            Student student = new Student();
//
//                            while (reader.hasNext()) {
//                                int event = reader.next();
//
//                                if (event == XMLStreamConstants.START_ELEMENT) {
//                                    String elementName = reader.getLocalName();
//                                    switch (elementName) {
//                                        case "id" -> student.setId(Integer.parseInt(reader.getElementText()));
//                                        case "student_name" -> student.setStudent_name(reader.getElementText());
//                                        case "group_number" -> student.setGroup_number(Integer.parseInt(reader.getElementText()));
//                                    }
//                                }
//                            }
//                            student.setMarks(getMarksById(file.getAbsolutePath(), student.getId()));
//                            students.add(student);
//                            reader.close();
//                        } catch (FileNotFoundException | XMLStreamException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//            }
//        } else {
//            System.out.println("Указанная директория не существует или не является директорией.");
//        }
//
//        return students;
        return getStudents(false);
    }

    private static Map<Integer, Integer> readMarks(XMLStreamReader reader) throws XMLStreamException {
        Map<Integer, Integer> marks = new HashMap<>();

        Subject subject = null;
        int eventType;

        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();

                switch (elementName) {
                    case "subject" -> subject = new Subject();
                    case "subject_id" -> {
                        int id = Integer.parseInt(reader.getElementText());
                        subject.setId(id);
                    }
                    case "mark" -> {
                        int mark = Integer.parseInt(reader.getElementText());
                        subject.setMark(mark);
                    }
                }
            }
            else if (eventType == XMLStreamConstants.END_ELEMENT) {
                String elementName = reader.getLocalName();

                if (elementName.equals("subject")) {
                    marks.put(subject.getId(), subject.getMark());
                }
                else if(elementName.equals("subjects")) {
                    System.out.println("Marks reading ended");
                    break;
                }
            }
        }

        return marks;
    }


//    private Map<Integer, Integer> getMarksById(String filePath, int id) throws FileNotFoundException, XMLStreamException {
//        Map<Integer, Integer> subjectsMap = new HashMap<>();
//
//        XMLInputFactory factory = XMLInputFactory.newInstance();
//        FileInputStream inputStream = new FileInputStream(filePath);
//        XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
//
//        boolean isTargetStudent = false;
//
//        while (reader.hasNext()) {
//            int event = reader.next();
//
//            if (event == XMLStreamConstants.START_ELEMENT) {
//                String elementName = reader.getLocalName();
//                if (elementName.equals("data")) {
//                    String studentIdAttr = reader.getAttributeValue(null, "student_id");
//                    if (studentIdAttr != null && studentIdAttr.equals(String.valueOf(id))) {
//                        isTargetStudent = true;
//                    } else {
//                        isTargetStudent = false;
//                    }
//                } else if (isTargetStudent && elementName.equals("subject_id")) {
//                    String subjectId = reader.getElementText();
//                    String mark = reader.getAttributeValue(null, "mark");
//                    subjectsMap.put(Integer.valueOf(subjectId), Integer.valueOf(mark));
//                }
//            }
//        }
//
//        reader.close();
//        return subjectsMap;
//    }

    @Override
    public List<Student> getDebtors() {
//        List<Student> debtors = getStudents(true);
//
//        return debtors.stream().sorted(Comparator.comparing(Student::getGroup_number)
//                .thenComparing(Student::getStudent_name))
//                .toList();
        return getStudents(true);
    }

    private List<Student> getStudents(boolean needToFindDebtors){
        List<Student> students = new ArrayList<>();
        File folder = new File(pathStudents.getAbsolutePath());
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".xml")) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                        XMLStreamReader reader = inputFactory.createXMLStreamReader(fileInputStream);

                        Student student = null;
                        int eventType;

                        while (reader.hasNext()) {
                            eventType = reader.next();

                            if (eventType == XMLStreamConstants.START_ELEMENT) {
                                String elementName = reader.getLocalName();

                                switch (elementName) {
                                    case "student" -> student = new Student();
                                    case "id" -> {
                                        int id = Integer.parseInt(reader.getElementText());
                                        student.setId(id);
                                    }
                                    case "group_number" -> {
                                        int groupNumber = Integer.parseInt(reader.getElementText());
                                        student.setGroup_number(groupNumber);
                                    }
                                    case "student_name" -> {
                                        String name = reader.getElementText();
                                        student.setStudent_name(name);
                                    }
                                    case "subjects" -> {
                                        Map<Integer, Integer> marks = readMarks(reader);
                                        student.setMarks(marks);
                                    }
                                }
                            } else if (eventType == XMLStreamConstants.END_ELEMENT) {
                                String elementName = reader.getLocalName();

                                if (elementName.equals("student")) {
                                    if(needToFindDebtors) {
                                        for (Map.Entry<Integer, Integer> subject : student.getMarks().entrySet()) {
                                            if (subject.getValue() < 4) {
                                                students.add(student);
                                                break;
                                            }
                                        }
                                    }
                                    else
                                        students.add(student);
                                }
                            }
                        }
                    } catch (XMLStreamException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        return students;
    }

}