import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentCollection extends ArrayList<Student> implements Iterable<Student>, Collection<Student> {
    public StudentCollection() {
        super();
    }

    public StudentCollection(ArrayList<Student> students) {
        super(students);
    }

    @Override
    public boolean add(Student student) {
        if(!super.contains(student)) {
            super.add(student);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<Student> iterator() {
        return super.iterator();
    }
}
