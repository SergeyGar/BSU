package com.company;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class CustomPanel extends JPanel {
    private PanelListener listener;

    CustomPanel() {super();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if((e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) ||
                        (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1)) {
                    PanelEvent event = new PanelEvent(CustomPanel.this, e.getClickCount(), e.getX(), e.getY());
                    try {
                        fireEvent(event);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });}

    public void addPanelListener(PanelListener listener) {this.listener = listener;}

    public void fireEvent(PanelEvent e) throws IOException {
        if(e.getNumberOfClicks() == 1)
            listener.rightClick(e);
        else
            listener.doubleClick(e);
    }
}

