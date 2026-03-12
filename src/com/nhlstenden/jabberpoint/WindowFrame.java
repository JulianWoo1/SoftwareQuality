package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Controllers.KeybindController;
import com.nhlstenden.jabberpoint.Controllers.MenuController;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame
{
    public WindowFrame() {
        this.setupWindow();
        this.setupMenu();
        this.setupKeybinds();
    }

    private void setupWindow() {
        add(new WindowPainter());
        setSize(new Dimension(1200, 800));
        setVisible(true);
    }
    
    private void setupMenu() {
        setMenuBar(new MenuController(this));
    }
    
    private void setupKeybinds() {
        addKeyListener(new KeybindController(this));
    }
}
