package com.nhlstenden.jabberpoint.Controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeybindController extends KeyAdapter
{
    private final PresentationActions actions;
    // S (Single Responsibility Principle):
    // This class is only responsible for handling keyboard input
    // and delegating actions. It does not handle presentation logic itself.

    public KeybindController(PresentationActions actions)
    {
        this.actions = actions;
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_PLUS:
                this.actions.nextSlide();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_MINUS:
                this.actions.previousSlide();
                break;
            case KeyEvent.VK_Q:
                this.actions.exitApplication();
                break;
            default:
                break;
        }
    }
}
