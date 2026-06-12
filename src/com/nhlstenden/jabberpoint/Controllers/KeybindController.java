package com.nhlstenden.jabberpoint.Controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeybindController extends KeyAdapter {

  private final PresentationActionsApi actions;

  public KeybindController(PresentationActionsApi actions) {
    this.actions = actions;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_PAGE_DOWN:
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_ENTER:
      case KeyEvent.VK_PLUS:
        actions.nextSlide();
        break;

      case KeyEvent.VK_PAGE_UP:
      case KeyEvent.VK_UP:
      case KeyEvent.VK_MINUS:
        actions.previousSlide();
        break;

      case KeyEvent.VK_Q:
        actions.exitApplication();
        break;
    }
  }
}