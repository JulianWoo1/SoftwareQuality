package com.nhlstenden.jabberpoint;

import javax.swing.*;
import java.awt.*;

public class WindowPainter extends JComponent implements PresentationChangeListener
{
    private final Presentation presentation;

    public WindowPainter(Presentation presentation)
    {
        this.presentation = presentation;
        this.presentation.addChangeListener(this);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        g.clearRect(0, 0, getWidth(), getHeight());
        this.presentation.draw(g, 0, 0);
    }

    @Override
    public void onPresentationChanged()
    {
        repaint();
    }
}
