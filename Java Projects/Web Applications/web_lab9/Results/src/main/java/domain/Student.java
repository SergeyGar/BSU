package domain;

import java.io.Serializable;
import java.util.Map;


/**
 * Class which contains information about student.
 * It implements the {@link Serializable}
 * @author Siarhei Harashchenka
 */
public class Student implements Serializable {
    /**
     * Field of ID of the student.
     */
    private int id;

     /**
     * Field of group number of the student.
     */
    private int group_number;

    /**
     * Field name of the student.
     */
    private String student_name;

    /**
     * The marks of the student, represented as a map of subject IDs to marks.
     */
    private Map<Integer, Integer> marks;

    /**
     * Default constructor for the Student class.
     */
    public Student(){}

    /**
     * Constructs a Student object with the specified
     * <ul>
     *     <li>ID</li>
     *     <li>group number</li>
     *     <li>name</li>
     *     <li>marks</li>
     * </ul>
     * @param id           the ID of the student
     * @param group_number the group number of the student
     * @param name         the name of the student
     * @param marks        the marks of the student
     */
    public Student(int id, int group_number, String name, Map<Integer, Integer> marks) {
        this.id = id;
        this.group_number = group_number;
        this.student_name = name;
        this.marks = marks;
    }

    /**
     * Returns the ID of the student.
     *
     * @return the ID of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the student.
     *
     * @param id the ID of the student
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the group number of the student.
     *
     * @return the group number of the student
     */
    public int getGroup_number() {
        return group_number;
    }

    /**
     * Sets the group number of the student.
     *
     * @param group_number the group number of the student
     */
    public void setGroup_number(int group_number) {
        this.group_number = group_number;
    }

    /**
     * Returns the name of the student.
     *
     * @return the name of the student
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     * Sets the name of the student.
     *
     * @param name the name of the student
     */
    public void setStudent_name(String name) {
        this.student_name = name;
    }

    /**
     * Sets the marks of the student.
     *
     * @param marks the marks of the student
     */
    public void setMarks(Map<Integer, Integer> marks) {
        this.marks = marks;
    }

    /**
     * Returns the marks of the student.
     *
     * @return the marks of the student
     */
    public Map<Integer, Integer> getMarks() {
        return marks;
    }
}
