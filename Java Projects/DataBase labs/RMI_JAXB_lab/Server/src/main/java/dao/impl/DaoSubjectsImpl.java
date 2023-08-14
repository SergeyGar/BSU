package dao.impl;

import dao.DaoSubjects;
import domain.Subject;
import domain.SubjectsWrapper;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DaoSubjectsImpl implements DaoSubjects, Serializable {

    File pathSubjects = new File("C:\\download\\JavaProjects\\RMI_JAXB_lab\\Server\\base\\subjects.xml");

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
        List<Subject> subjects = new ArrayList<>();

        try {
            File file = new File(pathSubjects.getAbsolutePath());

            JAXBContext jaxbContext = JAXBContext.newInstance(SubjectsWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SubjectsWrapper subjectsWrapper = (SubjectsWrapper) unmarshaller.unmarshal(file);

            if (subjectsWrapper != null && subjectsWrapper.getSubjects() != null) {
                subjects = subjectsWrapper.getSubjects();
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

    @Override
    public Subject getById(int id) {
        Optional<Subject> subject = Optional.empty();

        try {
            File file = new File(pathSubjects.getAbsolutePath());
            JAXBContext jaxbContext = JAXBContext.newInstance(SubjectsWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            SubjectsWrapper subjectsWrapper = (SubjectsWrapper) unmarshaller.unmarshal(file);

            if (subjectsWrapper != null && subjectsWrapper.getSubjects() != null) {
                subject = subjectsWrapper.getSubjects().stream().filter(subj -> subj.getId() == id).findFirst();
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return subject.get();
    }
}
