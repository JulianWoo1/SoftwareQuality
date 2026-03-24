package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Controllers.KeybindController;
import com.nhlstenden.jabberpoint.Controllers.MenuController;
import com.nhlstenden.jabberpoint.Controllers.PresentationActions;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame
{
    private final Presentation presentation;
    private final PresentationActions actions;

    public WindowFrame() {
        this.presentation = Presentation.getInstance();
        this.actions = new PresentationActions(this, this.presentation);
        this.setupWindow();
        this.setupMenu();
        this.setupKeybinds();
    }

    private void setupWindow()
    {
        add(new WindowPainter(this.presentation));
        setSize(new Dimension(1200, 800));
        setVisible(true);
    }
    
    private void setupMenu()
    {
        setMenuBar(new MenuController(this.actions));
    }
    
    private void setupKeybinds()
    {
        addKeyListener(new KeybindController(this.actions));
    }
}
