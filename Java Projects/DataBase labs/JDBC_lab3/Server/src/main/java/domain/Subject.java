package domain;

public class Subject {
    private int id;
    private String name;
    private Integer mark;
    public Subject() {}
    public Subject(int id, String name) {
        this.id = id;
        this.name= name;
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
