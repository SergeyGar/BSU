import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Reader {
    String defaultDirectory;
    JFileChooser fileChooser;

    public Reader(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open file:");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File(defaultDirectory));
    }
    public StudentCollection chooseData() throws FileNotFoundException, NoSuchElementException {
        StudentCollection readStudentList;
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            readStudentList = new StudentCollection();
            Scanner studentScanner = new Scanner(fileChooser.getSelectedFile());
            while (studentScanner.hasNextLine()) {
                readStudentList.add(Reader.parseLineIntoStudent(studentScanner.nextLine()));
            }
            return readStudentList;
        }
        else {
            return null;
        }
    }

    public static Student parseLineIntoStudent(String line) throws NoSuchElementException {
        if(line != null && !line.isEmpty()) {
            int recordBook, year, group;
            String surname;
            Scanner lineScanner = new Scanner(line);
            try {
                recordBook = lineScanner.nextInt();
                surname = lineScanner.next();
                year = lineScanner.nextInt();
                group = lineScanner.nextInt();
            } catch (NoSuchElementException e) {
                throw new NoSuchElementException("Недостаточно данных");
            }

            if (lineScanner.hasNext()) {
                throw new InputMismatchException("Избыток данных");
            } else {
                return new Student(recordBook, surname, year, group);
            }
        }
        else {
            throw new InputMismatchException("Введена пустая строка");
        }
    };
}
