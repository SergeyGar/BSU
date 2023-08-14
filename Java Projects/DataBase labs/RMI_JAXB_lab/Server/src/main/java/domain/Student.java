package domain;

import domain.support_map.MarksAdapter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "student")
public class Student implements Serializable {

    @XmlElement
    private int id;

    @XmlElement
    private int group;

    @XmlElement
    private String name;

    @XmlElement
    private Map<Integer, Integer> marks;
    public Student(){
        marks = new HashMap<>();
    }

    public Student(int id, int group, String name) {
        this.id = id;
        this.group = group;
        this.name = name;
    }
    public Student(int id, int group, String name, Map<Integer, Integer> marks) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.marks = marks;
    }
    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }


    public int getGroup() {
        return group;
    }

    @XmlElement(name = "group")
    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "marks")
    public void setMarks(Map<Integer, Integer> marks) {
        this.marks = marks;
    }

    @XmlJavaTypeAdapter(MarksAdapter.class)
    public Map<Integer, Integer> getMarks() {
        return marks;
    }
}
