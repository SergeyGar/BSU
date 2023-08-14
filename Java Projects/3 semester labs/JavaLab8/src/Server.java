import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends JFrame {
    private static final int maxAmountOfClients = 5;
    private static final Font customFont = new Font("Italic", Font.ITALIC, 20);
    private final JLabel filenameLabel = new JLabel("File wasn't received");
    private final JButton showTextButton = new JButton("Show encoded text");
    private String currFilename = null;
    private String text = null;
    private final ReentrantLock lock = new ReentrantLock();
    private ServerSocket serverSocket;
    private final ClientData[] clientResources = new ClientData[maxAmountOfClients];

    public static void main(String[] args) {
        Server server = new Server();
        server.setVisible(true);
        try {
            server.serverSocket = new ServerSocket(9331);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(server, "Server creation failed");
            System.exit(0);
        }
        for (int i = 0; i < maxAmountOfClients; ++i) {
            final int index = i;
            server.clientResources[i] = new ClientData();
            server.clientResources[i].thread = new Thread(() -> {
                outer:
                while (true) {
                    ClientData clientResources =
                            server.clientResources[index];
                    try {
                        clientResources.socket = server.serverSocket.accept();
                        // try
                        clientResources.objectOutputStream = new ObjectOutputStream(
                                clientResources.socket.getOutputStream());
                        clientResources.objectInputStream = new ObjectInputStream(
                                clientResources.socket.getInputStream());
                    } catch (IOException e) {
                        continue;
                    }
                    while (true) {
                        try {
                            if (clientResources.objectInputStream.readObject().equals("GET")) {
                                server.lock.lock();
                                boolean hasFile = server.text != null;
                                server.lock.unlock();
                                if (hasFile) {
                                    clientResources.objectOutputStream.writeObject("+");
                                } else {
                                    clientResources.objectOutputStream.writeObject("-");
                                    continue;
                                }
                                server.lock.lock();
                                clientResources.objectOutputStream.writeObject(server.text);
                                server.lock.unlock();
                            } else {
                                JOptionPane jOptionPane = new JOptionPane(
                                        "Client " + (index + 1) +
                                                " wants to rewrite the encoded file. Confirm?",
                                        JOptionPane.QUESTION_MESSAGE,
                                        JOptionPane.YES_NO_OPTION);
                                JDialog dialog = jOptionPane.createDialog(server,
                                        "Select an option");
                                dialog.setVisible(true);
                                if (jOptionPane.getValue() != null &&
                                        jOptionPane.getValue().equals(
                                                JOptionPane.YES_OPTION)) {
                                    clientResources.objectOutputStream.writeObject("+");
                                } else {
                                    clientResources.objectOutputStream.writeObject("-");
                                    continue;
                                }
                                server.lock.lock();
                                server.currFilename = (String) clientResources.objectInputStream.readObject();
                                server.text = (String) clientResources.objectInputStream.readObject();
                                server.filenameLabel.setText(server.currFilename +
                                        " from Client " + (index + 1));
                                server.lock.unlock();
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            if (server.lock.isLocked())
                                server.lock.unlock();
                            JOptionPane.showMessageDialog(server,
                                    "Lost connection with Client" + (index + 1));
                            continue outer;
                        }
                    }
                }
            });
            server.clientResources[i].thread.start();
        }
    }

    public Server() {
        super("Server");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setUpObjects();

        showTextButton.setSize(200, 100);
    }

    void setUpObjects() {
        setLayout(new BorderLayout());
        filenameLabel.setFont(customFont);
        filenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(filenameLabel, BorderLayout.NORTH);
        showTextButton.setFont(customFont);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(showTextButton);
        add(panel, BorderLayout.CENTER);
        showTextButton.addActionListener(e -> {
            lock.lock();
            if (text != null) {
                FileTextWindow fileViewer = new FileTextWindow(text);
                fileViewer.setVisible(true);
            }
            lock.unlock();
        });
    }

    static class ClientData {
        Socket socket;
        Thread thread;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;
    }
}
