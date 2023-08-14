package domain;

import java.io.Serializable;

/**
 * Class which contains information about subject.
 * It implements the {@link Serializable}
 * @author Siarhei Harashchenka
 */
public class Subject implements Serializable {

    /**
     * The ID of the subject.
     */
    private int id;

    /**
     * The name of the subject.
     */
    private String name;

    /**
     * The mark obtained in the subject.
     */
    private Integer mark;

    /**
     * Default constructor for the Subject class.
     */
    public Subject() {
    }

    /**
     * Constructs a Subject object with the specified ID and name.
     *
     * @param id   the ID of the subject
     * @param name the name of the subject
     */
    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of the subject.
     *
     * @return the ID of the subject
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the subject.
     *
     * @param id the ID of the subject
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the subject.
     *
     * @return the name of the subject
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the subject.
     *
     * @param name the name of the subject
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the mark obtained in the subject.
     *
     * @return the mark obtained in the subject
     */
    public Integer getMark() {
        return mark;
    }

    /**
     * Sets the mark obtained in the subject.
     *
     * @param mark the mark obtained in the subject
     */
    public void setMark(Integer mark) {
        this.mark = mark;
    }
}
