package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Student implements Serializable {

    private int id;


    private int group;


    private String name;


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


    public void setId(int id) {
        this.id = id;
    }


    public int getGroup() {
        return group;
    }


    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setMarks(Map<Integer, Integer> marks) {
        this.marks = marks;
    }


    public Map<Integer, Integer> getMarks() {
        return marks;
    }
}
