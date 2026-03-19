package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.WindowFrame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeybindController extends KeyAdapter
{
    private WindowFrame windowFrame;
    
    public KeybindController(WindowFrame window)
    {
        this.windowFrame = window;
    }

    public void keyPressed(KeyEvent keyEvent) {
        Presentation presentation = Presentation.getInstance();
        switch(keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                presentation.nextSlide();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                presentation.previousSlide();
                break;
            case 'q':
            case 'Q':
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
