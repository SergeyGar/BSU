package com.company;

import java.awt.*;

class PanelEvent extends AWTEvent {
    private final int numberOfClicks;
    private final int x;
    private final int y;

    public int getNumberOfClicks() {return numberOfClicks;}

    public int getX() {return x;}

    public int getY() {return y;}

    public PanelEvent(CustomPanel p, int numberOfClicks, int x, int y) {
        super(p, PANEL_EVENT);
        this.numberOfClicks = numberOfClicks;
        this.x = x;
        this.y = y;
    }
    public static final int PANEL_EVENT = AWTEvent.RESERVED_ID_MAX + 1;
}


