package com.nhlstenden.jabberpoint.Controllers;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;

public class MenuController extends MenuBar {
  public MenuController(PresentationActions actions) {
    Menu file = new Menu("File");
    add(file, "Open", actions::openPresentation);
    add(file, "New", actions::newPresentation);
    add(file, "Save", actions::savePresentation);
    add(file, "Exit", actions::exitApplication);

    Menu view = new Menu("View");
    add(view, "Next", actions::nextSlide);
    add(view, "Previous", actions::previousSlide);
    add(view, "Goto", actions::gotoSlide);

    Menu help = new Menu("Help");
    add(help, "About", actions::showAbout);

    add(file);
    add(view);
    add(help);
  }

  private void add(Menu menu, String name, Runnable action) {
    MenuItem item = new MenuItem(name);
    item.addActionListener(e -> action.run());
    menu.add(item);
  }
}
