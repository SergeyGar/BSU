package dao.impl;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import dao.DaoStudents;
import domain.Student;

import java.io.*;
import java.util.*;

public class DaoStudentsImpl implements DaoStudents, Serializable {


    File base = new File("C:\\download\\JavaProjects\\RMI_JAXB_lab\\Server\\base");

    File pathStudents = new File("C:\\download\\JavaProjects\\RMI_JAXB_lab\\Server\\base\\students");

    public DaoStudentsImpl(){
        if (!pathStudents.exists()) {
            pathStudents.mkdirs();
        }
    }

    @Override
    public int getNextStudentId() {
        int maxId = 0;

        try {
            File directory = new File(pathStudents.getAbsolutePath());

            if (directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".xml")) {
                            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
                            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                            Student student = (Student) unmarshaller.unmarshal(file);
                            if (student.getId() > maxId) {
                                maxId = student.getId();
                            }
                        }
                    }
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return maxId + 1;
    }

    @Override
    public void addStudent(Student student){
        try {
            String fileName = student.getName() + "_" +
                    student.getId() + ".xml";
            String filePath = pathStudents + "/" + fileName;

            JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);

            Marshaller marshaller = jaxbContext.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            FileOutputStream fileOutputStream = new FileOutputStream(filePath);

            marshaller.marshal(student, fileOutputStream);
        } catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteStudent(Student student){
        File file = new File(pathStudents, student.getName() + "_" +
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
        return getStudents(false);
    }

    @Override
    public List<Student> getDebtors() {
//        List<Student> debtors = getStudents(true);
//
//        return debtors.stream().sorted(Comparator.comparing(Student::getGroup_number)
//                .thenComparing(Student::getStudent_name))
//                .toList();
        List<Student> debtors = getStudents(true);

        return debtors.stream().sorted(Comparator.comparing(Student::getGroup)
                        .thenComparing(Student::getName))
                .toList();
    }

    private List<Student> getStudents(boolean needToFindDebtors) {
        List<Student> students = new ArrayList<>();

        try {
            File directory = new File(pathStudents.getAbsolutePath());

            if (directory.isDirectory()) {
                File[] files = directory.listFiles();

                if (files != null) {
                    for (File file : files) {
                        if (file.isFile() && file.getName().endsWith(".xml")) {
                            Student student = readStudentFromFile(file);
                            if (needToFindDebtors) {
                                if (student.getMarks().values().stream()
                                        .anyMatch(mark -> mark < 4))
                                    students.add(student);
                            } else
                                students.add(student);
                        }
                    }
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return students;
    }

    private static Student readStudentFromFile(File file) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Student) unmarshaller.unmarshal(file);
    }

}