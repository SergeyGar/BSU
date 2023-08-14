package domain;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "subject")
public class Subject implements Serializable {

    @XmlElement
    private int id;

    @XmlElement
    private String name;

    @XmlElement
    private Integer mark;
    public Subject() {}
    public Subject(int id, String name) {
        this.id = id;
        this.name= name;
    }

    public Subject(int id, String name, int mark) {
        this.id = id;
        this.name= name;
        this.mark = mark;
    }
    public int getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }
    public Integer getMark() {
        return mark;
    }

    @XmlElement(name = "mark")
    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
