import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

public class GUI extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int WIDTH = 600;
    final int HEIGHT = 400;
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Файл");
    JMenu menuAction = new JMenu("Редактировать");
    JMenuItem openItem = new JMenuItem("Открыть");
    JMenuItem processItem = new JMenuItem("Отсортировать");
    JMenuItem addStudentItem = new JMenuItem("Добавить студента");
    DataHandler dataHandler = new DataHandler();
    DefaultListModel<Student> readDataModel = new DefaultListModel<>();
    DefaultListModel<Student> processedDataModel = new DefaultListModel<>();
    JList<Student> readDataList = new JList<>();
    JList<Student> processedDataList = new JList<>();

    GUI() {
        super("Lab j9");
        setBounds((screenSize.width - WIDTH) / 2, (screenSize.height - HEIGHT) / 2, WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        readDataList.setModel(readDataModel);
        processedDataList.setModel(processedDataModel);

        readDataList.setPreferredSize(new Dimension(250, HEIGHT));
        processedDataList.setPreferredSize(new Dimension(250, HEIGHT));
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Reader reader = new Reader("src");
                    StudentCollection students = reader.chooseData();
                    if (students != null) {
                        dataHandler.clear();
                        dataHandler.addStudents(students);
                        dataHandler.copyToModel(readDataModel, DataHandler.UNSORTED_STUDENTS);
                    }
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Не удалось найти файл с таким именем",
                            "Сообщение", JOptionPane.PLAIN_MESSAGE);
                } catch (NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(null, "Некорректные данные для ввода",
                            "Сообщение", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        processItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!readDataModel.isEmpty()) {
                    String yearToSort = JOptionPane.showInputDialog(null,
                            "Студентов какого курса отсортировать?", "Введите курс", JOptionPane.PLAIN_MESSAGE);
                    if (yearToSort != null) {
                        try {
                            dataHandler.sortStudents(new GroupSurnameComparator(), Integer.parseInt(yearToSort));
                            dataHandler.copyToModel(processedDataModel, DataHandler.SORTED_STUDENTS);
                        } catch (IllegalArgumentException ex) {
                            if (!yearToSort.isEmpty()) {
                                JOptionPane.showMessageDialog(GUI.this, ex.getMessage(),
                                        "Сообщение", JOptionPane.PLAIN_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(GUI.this, "Введена пустая строка",
                                        "Сообщение", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "Список пуст",
                            "Сообщение", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        addStudentItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String studentLine = JOptionPane.showInputDialog(null,
                            "Введите через пробел: номер зачётки, фамилию, курс, группу ",
                            "Добавить студента", JOptionPane.PLAIN_MESSAGE);
                    if (studentLine != null) {
                        Student student = Reader.parseLineIntoStudent(studentLine);
                        if (dataHandler.addStudent(student)) {
                            readDataModel.addElement(student);
                        } else {
                            JOptionPane.showMessageDialog(GUI.this, "Данный студент уже есть в списке",
                                    "Сообщение", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                } catch (NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(GUI.this, ex.getMessage(),
                            "Сообщение", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        menuFile.add(openItem);
        menuAction.add(processItem);
        menuAction.add(addStudentItem);
        menuBar.add(menuFile);
        menuBar.add(menuAction);
        setJMenuBar(menuBar);
        add(readDataList, BorderLayout.WEST);
        add(processedDataList, BorderLayout.EAST);
    }
}
