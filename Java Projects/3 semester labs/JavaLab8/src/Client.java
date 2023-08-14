import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Client extends JFrame implements Runnable {
    private static final Font customFont = new Font("Italic", Font.ITALIC, 20);
    private static final int a = 3;
    private static final int b = 10000;
    private final JLabel filenameLabel = new JLabel("File is not chosen");
    private final JButton sendButton = new JButton("SendToServer");
    private final JButton getButton = new JButton("GetFromServer");
    private final JFileChooser fileChooser = new JFileChooser("C:\\download\\JavaProjects\\JavaLab8");
    private String text;
    private Socket socket;
    private Thread serverCommThread;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Operation currOperation = Operation.NONE;

    enum Operation {
        NONE,
        SEND,
        GET
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.setVisible(true);
        try {
            client.socket = new Socket("localhost", 9331);
            client.socket.setSoTimeout(5000);
            client.objectInputStream = new ObjectInputStream(
                    client.socket.getInputStream());
            client.objectOutputStream = new ObjectOutputStream(
                    client.socket.getOutputStream());
            client.socket.setSoTimeout(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(client, "Can not connect to server");
            System.exit(0);
        }
        client.serverCommThread = new Thread(client);
        client.serverCommThread.start();
    }

    public Client() {
        super("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        fillContainer();

        sendButton.setSize(200, 100);
        getButton.setSize(200, 100);
    }

    void fillContainer() {
        setLayout(new BorderLayout());
        filenameLabel.setFont(customFont);
        filenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panel = new JPanel();
        panel.add(sendButton);
        panel.add(getButton);
        initButton(sendButton, new SendEventListener());
        initButton(getButton, new GetEventListener());
        add(panel, BorderLayout.CENTER);

    }

    void initButton(JButton button, ActionListener actionListener) {
        button.setFont(customFont);
        button.setSize(200, 100);
        button.addActionListener(actionListener);
    }

    void encodeText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            stringBuilder.append((char) (a * text.charAt(i) + b));
        }
        text = stringBuilder.toString();
    }

    void decodeText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            int charCode = text.charAt(i);
            charCode -= b;
            while (charCode < 0) {
                charCode += 65536;
            }
            while (charCode % a != 0) {
                charCode += 65536;
            }
            stringBuilder.append((char) (charCode / a));
        }
        text = stringBuilder.toString();
    }

    boolean chooseFile() {
        fileChooser.setDialogTitle("File selection");
        fileChooser.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        return fileChooser.showDialog(this, "Select") == JFileChooser.APPROVE_OPTION;
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (serverCommThread == me) {
            sendButton.setEnabled(true);
            getButton.setEnabled(true);
            try {
                synchronized (this) {
                    currOperation = Operation.NONE;
                    while (currOperation == Operation.NONE) {
                        wait();
                    }
                }
            } catch (InterruptedException e) {
                continue;
            }
            if (currOperation == Operation.SEND) {
                if (!chooseFile()) {
                    continue;
                }
                try {
                    text = new String(Files.readAllBytes(Paths.
                            get(fileChooser.getSelectedFile().getAbsolutePath())));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "File reading failed");
                    continue;
                }
                encodeText();
                try {
                    objectOutputStream.writeObject("SEND");
                    objectOutputStream.flush();
                    if (objectInputStream.readObject().equals("-")) {
                        JOptionPane.showMessageDialog(this, "Permission Denied");
                        continue;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Connection error");
                    continue;
                }
                try {
                    objectOutputStream.writeObject(fileChooser.getSelectedFile().getName());
                    objectOutputStream.writeObject(text);
                    objectOutputStream.flush();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Connection error");
                }
                filenameLabel.setText("Sent " +
                        fileChooser.getSelectedFile().getName());
            } else {
                try {
                    if (!chooseFile()) {
                        continue;
                    }
                    objectOutputStream.writeObject("GET");
                    objectOutputStream.flush();
                    if (objectInputStream.readObject().equals("-")) {
                        JOptionPane.showMessageDialog(this, "Server doesn't have data");
                        continue;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Connection error");
                    continue;
                }
                try {
                    text = (String) objectInputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(this, "Connection error");
                    continue;
                }
                decodeText();
                try (FileWriter fileWriter = new FileWriter(
                        fileChooser.getSelectedFile(), false)) {
                    fileWriter.write(text);
                    fileWriter.flush();
                } catch (IOException ex) {
                    filenameLabel.setText("File writing failed");
                }
                filenameLabel.setText("Got " +
                        fileChooser.getSelectedFile().getName());
            }
        }
    }

    void prepareToOperation(Operation op) {
        sendButton.setEnabled(false);
        getButton.setEnabled(false);
        synchronized (this) {
            currOperation = op;
            notify();
        }
    }

    class SendEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prepareToOperation(Operation.SEND);
        }
    }

    class GetEventListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            prepareToOperation(Operation.GET);
        }
    }
}
