import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.NoSuchFileException;


public class Canvas extends JFrame implements ActionListener {
    private final JButton black;
    private final JButton green;
    private final JButton red;
    private final JButton saveImage;
    private final JButton openImage;
    //private final JButton clearButton;
    private final JScrollPane pane;
    private final FileManager fileManager = new FileManager(this);

    private final PanelForDrawing panel;

    public Canvas() {
        setLayout(new BorderLayout());
        final Color BACKGROUND = Color.CYAN;
        Font font = new Font("Italic", Font.ITALIC, 22);

        black = new JButton("Black");
        green = new JButton("Green");
        red = new JButton("Red");

        black.setFont(font);
        green.setFont(font);
        red.setFont(font);

        black.addActionListener(this);
        green.addActionListener(this);
        red.addActionListener(this);

        saveImage = new JButton("Save");
        saveImage.setFont(font);

        openImage = new JButton("Open image");
        openImage.setFont(font);

        saveImage.addActionListener(this);
        openImage.addActionListener(this);

//        clearButton = new JButton("Clear");
//        clearButton.setFont(font);
//        clearButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(black);
        panel.add(green);
        panel.add(red);
        panel.add(saveImage);
        panel.add(openImage);
        //panel.add(clearButton);
        add(panel, BorderLayout.NORTH);

        this.panel = new PanelForDrawing(1200, 900);
        this.panel.setPreferredSize(new Dimension(1200, 900));
        //this.panel.setBackground(BACKGROUND);

        pane = new JScrollPane(this.panel);
        add(pane, BorderLayout.CENTER);

//        panel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
//            }
//        });
//
//        panel.addMouseMotionListener(new MouseAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                super.mouseDragged(e);
//                repaint();
//                //board.mouseDragged(e);
//            }
//        });
    }

    public class PanelForDrawing extends JPanel {

        private BufferedImage bufferedImage;
        private Graphics imageGraphics;
        private Color currentColor = Color.black;
        private Point previousPoint;
        private Point newPoint;

        public PanelForDrawing(int width, int height) {

            setPreferredSize(new Dimension(width, height));
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            imageGraphics = bufferedImage.createGraphics();
            imageGraphics.setColor(currentColor);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    previousPoint = e.getPoint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    newPoint = e.getPoint();
                    imageGraphics.drawLine(previousPoint.x, previousPoint.y, newPoint.x, newPoint.y);
                    previousPoint = newPoint;
                    repaint();
                }
            });
        }

//        public void clear() {
//            imageGraphics.clearRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());
//        }

        public void setColor(Color color) {
            imageGraphics.setColor(color);
            currentColor = color;
        }

        public BufferedImage getImage() {
            return bufferedImage;
        }

        public void setImage(ImageIcon image) {
            setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight()));
            bufferedImage = new BufferedImage(image.getIconWidth(), image.getIconHeight(), BufferedImage.TYPE_INT_RGB);
            imageGraphics = bufferedImage.createGraphics();
            imageGraphics.setColor(currentColor);
            imageGraphics.drawImage(image.getImage(), 0, 0, null);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (bufferedImage != null) {
                g.drawImage(bufferedImage, 0, 0, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == black) {
            panel.setColor(Color.BLACK);
        } else if (e.getSource() == green) {
            panel.setColor(Color.GREEN);
        } else if (e.getSource() == red) {
            panel.setColor(Color.RED);
        } else if (e.getSource() == saveImage) {
            try {
                File file = fileManager.performFileDialog("Images (*.jpg)", "jpg", FileAction.SAVE);
                if (file == null) {
                    return;
                }
                if (!file.exists()) {
                    //throw new NoSuchFileException("No Such file!");
                }
                ImageIO.write(panel.getImage(), "jpg", file);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(Canvas.this, ex.getMessage(), "Couldn't save file", JOptionPane.PLAIN_MESSAGE);
            }
        }
        else if (e.getSource() == openImage) {
            try {
                File file = fileManager.performFileDialog("Text files (*.jpg)", "jpg", FileAction.OPEN);
                if (file == null) {
                    return;
                }
                if (!file.exists()) {
                    throw new NoSuchFileException("No Such file!");
                }
                panel.setImage(new ImageIcon(file.getName()));
                pane.updateUI();
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(Canvas.this, ex.getMessage(), "Couldn't open file", JOptionPane.PLAIN_MESSAGE);
            }
        } //else if (e.getSource() == clearButton) {
//            panel.clear();
//            repaint();
//            //board.clear();
//        }
    }
}
