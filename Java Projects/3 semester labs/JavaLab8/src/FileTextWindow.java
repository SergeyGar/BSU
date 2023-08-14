import javax.swing.*;
import java.awt.*;

public class FileTextWindow extends JFrame {
    public FileTextWindow(String text) {
        super("File viewer");
        setSize(800, 400);
        JTextArea textArea = new JTextArea(text);
        textArea.setSize(getWidth() - 30, getHeight() - 15);
        textArea.setEditable(false);
        textArea.setFont(new Font("Italic", Font.ITALIC, 20));
        textArea.setLineWrap(true);
        JPanel panel = new JPanel();
        panel.add(textArea);
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
    }
}

