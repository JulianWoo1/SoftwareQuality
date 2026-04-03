package com.nhlstenden.jabberpoint.Controllers;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class MenuController extends MenuBar
{
    private final Map<String, Map<String, Runnable>> menuBar;

    public MenuController(PresentationActions actions)
    {
        this.menuBar = new LinkedHashMap<>() {{
            put("File", new LinkedHashMap<>() {{
                put("Open", actions::openPresentation);
                put("New", actions::newPresentation);
                put("Save", actions::savePresentation);
                put("Exit", actions::exitApplication);
            }});
            put("View", new LinkedHashMap<>() {{
                put("Next", actions::nextSlide);
                put("Previous", actions::previousSlide);
                put("Goto", actions::gotoSlide);
            }});
            put("Help", new LinkedHashMap<>() {{
                put("About", actions::showAbout);
            }});
        }};

        for (Map.Entry<String, Map<String, Runnable>> menuItem : this.menuBar.entrySet())
        {
            Menu menu = new Menu(menuItem.getKey());

            for (Map.Entry<String, Runnable> menuItemItem : menuItem.getValue().entrySet())
            {
                MenuItem item = new MenuItem(menuItemItem.getKey(), new MenuShortcut(menuItemItem.getKey().charAt(0)));
                item.addActionListener((action) -> menuItemItem.getValue().run());
                menu.add(item);
            }

            add(menu);
        }
    }
}
