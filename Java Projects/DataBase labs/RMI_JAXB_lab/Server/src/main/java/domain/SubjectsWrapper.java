package domain;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "subjects")
//@XmlSeeAlso({Subject.class})
public class SubjectsWrapper {

    @XmlElement(name = "subject")
    private List<Subject> subjects;

    public SubjectsWrapper(){}

    public List<Subject> getSubjects() { return subjects;}

    @XmlElement(name = "subject")
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}