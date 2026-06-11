package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Serializer.PresentationSerializer;

import javax.swing.*;

public class PresentationService
{
    private final Presentation presentation;
    private final PresentationSerializer serializer;
    private final JFrame frame;

    public PresentationService(Presentation presentation,
                               PresentationSerializer serializer,
                               JFrame frame)
    {
        this.presentation = presentation;
        this.serializer = serializer;
        this.frame = frame;
    }

    public void open()
    {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION)
        {
            try {
                presentation.clearPresentation();
                serializer.loadToPresentation(
                        chooser.getSelectedFile().getAbsolutePath(),
                        presentation
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }
    }

    public void save()
    {
        JFileChooser chooser = new JFileChooser();

        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
        {
            try {
                serializer.saveFromPresentation(
                        chooser.getSelectedFile().getAbsolutePath(),
                        presentation
                );
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }
    }

    public void clear()
    {
        presentation.clearPresentation();
    }

    public void next()
    {
        presentation.nextSlide();
    }

    public void previous()
    {
        presentation.previousSlide();
    }

    public void goTo()
    {
        String input = JOptionPane.showInputDialog("Slide number?");
        if (input == null) return;

        try {
            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= presentation.getSlides().size())
            {
                JOptionPane.showMessageDialog(frame, "Invalid slide number");
                return;
            }

            presentation.setCurrentSlide(index);
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number");
        }
    }

    public void about()
    {
        JOptionPane.showMessageDialog(frame, "JabberPoint");
    }

    public void exit()
    {
        frame.dispose();
        System.exit(0);
    }
}