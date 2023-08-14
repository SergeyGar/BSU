import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SGUI extends JFrame implements ActionListener {

    public SGUI() {
        Font font = new Font("Italic", Font.ITALIC, 20);

        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
