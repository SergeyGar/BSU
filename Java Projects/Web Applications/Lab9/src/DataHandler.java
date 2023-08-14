import javax.swing.*;
import java.util.Comparator;

public class DataHandler {
    private StudentCollection students;
    private StudentCollection sortedStudents;
    public static final boolean UNSORTED_STUDENTS = false;
    public static final boolean SORTED_STUDENTS = true;

    DataHandler() {
        students = new StudentCollection();
    }

    DataHandler(StudentCollection students) {
        this.students = students;
    }

    public StudentCollection getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        return students.add(student);
    }

    public boolean addStudents(StudentCollection studentCollection) {
        return students.addAll(studentCollection);
    }

    public void copyToModel(DefaultListModel<Student> listModel, boolean isSorted) {
        listModel.clear();
        if(isSorted) {
            if(sortedStudents != null) {
                for (Student student : sortedStudents) {
                    listModel.addElement(student);
                }
            } else {
                throw new NullPointerException("Студенты не были отсортированы");
            }
        } else {
            for (Student student : students) {
                listModel.addElement(student);
            }
        }
    }

    public void sortStudents(Comparator<Student> comparator, int yearToSort) {
        if (yearToSort > 0) {
            sortedStudents = new StudentCollection(students);
            sortedStudents.removeIf((Student student) -> student.getYear() != yearToSort);
            if(!sortedStudents.isEmpty()) {
                sortedStudents.sort(comparator);
            } else {
                throw new IllegalArgumentException("Не найдено студентов " + yearToSort + " курса");
            }
        } else {
            throw new IllegalArgumentException("Номер курса введён неверно");
        }
    }

    public void clear() {
        students.clear();
    }
}

