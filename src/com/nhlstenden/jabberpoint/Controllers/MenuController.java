package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MenuController extends MenuBar
{
    private WindowFrame windowFrame;
    private HashMap<String, HashMap<String, Runnable>> menuBar = new HashMap<>(){{
        put("File", new HashMap<>(){{
            put("Open",() -> {
                JFileChooser chooser = new JFileChooser();

                if(chooser.showOpenDialog(windowFrame) == JFileChooser.APPROVE_OPTION)
                {
                    Presentation.getInstance().loadPresentationFromXMLFile(chooser.getSelectedFile().getAbsolutePath());
                }
            });
            put("New", () -> Presentation.getInstance().clearPresentation());
            put("Save",() -> {
                JFileChooser chooser = new JFileChooser();

                if(chooser.showSaveDialog(windowFrame) == JFileChooser.APPROVE_OPTION)
                {
                    Presentation.getInstance().savePresentationToXMLFile(chooser.getSelectedFile().getAbsolutePath());
                }
            });
            put("Exit", () -> System.exit(0));
        }});
        put("View", new HashMap<>(){{
            put("Next", () -> Presentation.getInstance().nextSlide());
            put("Previous", () -> Presentation.getInstance().previousSlide());
            put("Goto", () -> {
                String pageNumberStr = JOptionPane.showInputDialog("Page Number?");
                int pageNumber = Integer.parseInt(pageNumberStr);
                Presentation.getInstance().setCurrentSlide(pageNumber - 1);
            });
        }});
        put("Help", new HashMap<>(){{
            put("About", () ->
            {
                
            });
        }});
    }};
    
    
    public MenuController(WindowFrame window)
    {
        this.windowFrame = window;
        
        for (Map.Entry<String, HashMap<String, Runnable>> menuItem : menuBar.entrySet())
        {
            Menu menu = new Menu(menuItem.getKey());
            
            for(Map.Entry<String, Runnable> menuItemItem : menuItem.getValue().entrySet())
            {
                MenuItem item = new MenuItem(menuItemItem.getKey(), new MenuShortcut(menuItemItem.getKey().charAt(0)));
                item.addActionListener((action) -> {
                    menuItemItem.getValue().run();
                });
                menu.add(item);
            }
            
            add(menu);
        }
    }
}
