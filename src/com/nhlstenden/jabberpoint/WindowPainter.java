package com.nhlstenden.jabberpoint;

import javax.swing.*;
import java.awt.*;

public class WindowPainter extends JComponent
{
    private static WindowPainter instance;

    public WindowPainter()
    {
        instance = this;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());
        Presentation.getInstance().draw(g, 0, 0);
    }

    public static void DoRepaint()
    {
        instance.repaint();
    }
}
