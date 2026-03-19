package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.WindowFrame;

import java.awt.event.KeyAdapter;

public class KeybindController extends KeyAdapter
{
    private WindowFrame windowFrame;
    
    public KeybindController(WindowFrame window)
    {
        this.windowFrame = window;
    }
}
