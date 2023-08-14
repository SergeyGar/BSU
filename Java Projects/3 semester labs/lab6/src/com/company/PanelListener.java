package com.company;

import java.io.IOException;
import java.util.EventListener;

public interface PanelListener extends EventListener {
    void doubleClick(PanelEvent e) throws IOException;
    void rightClick(PanelEvent e) throws  IOException;
}

