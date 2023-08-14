package domain;

import java.util.Map;

public class Student {
    private int id;
    private int group_number;
    private String student_name;
    private Map<Integer, Integer> marks;
    public Student(){}
    public Student(int id, int group_number, String name, Map<Integer, Integer> marks) {
        this.id = id;
        this.group_number = group_number;
        this.student_name = name;
        this.marks = marks;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_number() {
        return group_number;
    }

    public void setGroup_number(int group_number) {
        this.group_number = group_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String name) {
        this.student_name = name;
    }

    public void setMarks(Map<Integer, Integer> marks) {
        this.marks = marks;
    }

    public Map<Integer, Integer> getMarks() {
        return marks;
    }
}
