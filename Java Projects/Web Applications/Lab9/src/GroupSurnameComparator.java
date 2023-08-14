import java.util.Comparator;

public class GroupSurnameComparator implements Comparator<Student> {

    @Override
    public int compare(Student thisStudent, Student otherStudent) {
        if (thisStudent.getGroup() == otherStudent.getGroup()) {
            if(thisStudent.getSurname().equals(otherStudent.getSurname())) {
                return thisStudent.getRecordBook() - otherStudent.getRecordBook();
            }
            else {
                return thisStudent.getSurname().compareTo(otherStudent.getSurname());
            }
        } else {
            return thisStudent.getGroup() - otherStudent.getGroup();
        }
    }
}
