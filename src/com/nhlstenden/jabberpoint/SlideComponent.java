package com.nhlstenden.jabberpoint;

import java.awt.*;

public interface SlideComponent
{
    // I (Interface Segregation Principle):
    // This interface is small and focused.
    // Classes only implement what they need draw,
    // instead of being forced to implement unused methods.
    void draw(Graphics graphics, int x, int y);
}
