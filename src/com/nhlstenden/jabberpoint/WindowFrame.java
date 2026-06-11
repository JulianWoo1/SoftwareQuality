package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Controllers.*;
import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame
{
    public WindowFrame(Presentation presentation)
    {
        XMLSerializer serializer = new XMLSerializer();

        PresentationService service =
                new PresentationService(presentation, serializer, this);

        PresentationActions actions = new PresentationActions(service);

        add(new WindowPainter(presentation));

        setSize(new Dimension(1200, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("JabberPoint");

        setMenuBar(new MenuController(actions));
        addKeyListener(new KeybindController(actions));

        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
    }
}