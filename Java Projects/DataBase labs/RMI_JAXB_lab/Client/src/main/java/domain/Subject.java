package domain;

import java.io.Serializable;


public class Subject implements Serializable {


    private int id;

    private String name;

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


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
    public Integer getMark() {
        return mark;
    }


    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
