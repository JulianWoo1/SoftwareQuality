package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.WindowFrame;

import javax.swing.*;

public class PresentationActions
{
    private final WindowFrame windowFrame;
    private final Presentation presentation;

    public PresentationActions(WindowFrame windowFrame, Presentation presentation)
    {
        this.windowFrame = windowFrame;
        this.presentation = presentation;
    }

    public void openPresentation()
    {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this.windowFrame) == JFileChooser.APPROVE_OPTION)
        {
            this.presentation.loadPresentationFromXMLFile(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    public void newPresentation()
    {
        this.presentation.clearPresentation();
    }

    public void savePresentation()
    {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showSaveDialog(this.windowFrame) == JFileChooser.APPROVE_OPTION)
        {
            this.presentation.savePresentationToXMLFile(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    public void nextSlide()
    {
        this.presentation.nextSlide();
    }

    public void previousSlide()
    {
        this.presentation.previousSlide();
    }

    public void gotoSlide()
    {
        String pageNumberStr = JOptionPane.showInputDialog("Page Number?");
        if (pageNumberStr == null)
        {
            return;
        }

        try
        {
            int pageNumber = Integer.parseInt(pageNumberStr);
            this.presentation.setCurrentSlide(pageNumber - 1);
        } catch (NumberFormatException ignored)
        {
            JOptionPane.showMessageDialog(this.windowFrame, "Please enter a valid number.");
        }
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(this.windowFrame, "JabberPoint");
    }

    public void exitApplication()
    {
        System.exit(0);
    }
}
