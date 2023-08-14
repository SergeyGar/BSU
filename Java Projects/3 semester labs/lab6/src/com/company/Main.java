package com.company;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(600, 600);

        JLabel label = new JLabel();
        label.setSize(200, 100);


        CustomPanel customPanel = new CustomPanel();
        customPanel.setSize(600, 600);
        customPanel.add(label);
        mainFrame.add(customPanel);


        customPanel.addPanelListener(new PanelListener() {
            @Override
            public void doubleClick(PanelEvent e) throws IOException {
                label.setText("Double click on X: " + e.getX() + " Y: " + e.getY());
            }

            @Override
            public void rightClick(PanelEvent e) throws IOException {
                label.setText("Right click on X: " + e.getX() + " Y: " + e.getY());
            }
        });

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}