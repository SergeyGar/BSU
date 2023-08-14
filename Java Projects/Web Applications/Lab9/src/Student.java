public class Student {
    private int recordBook;
    private String surname;
    private int year;
    private int group;

    Student(int recordBook, String surname, int year, int group) {
        this.recordBook = recordBook;
        this.surname = surname;
        this.year = year;
        this.group = group;
    }

    public int getRecordBook() {
        return recordBook;
    }

    public String getSurname() {
        return surname;
    }

    public int getYear() {
        return year;
    }

    public int getGroup() {
        return group;
    }

    public String toString() {
        return recordBook + " " + surname + " " + year + " " + group;
    }

    @Override
    public boolean equals(Object object) {
        boolean isSame = false;

        if (object instanceof Student) {
            isSame = this.recordBook == ((Student) object).recordBook;
        }

        return isSame;
    }
}
