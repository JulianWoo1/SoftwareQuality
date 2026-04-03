package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.awt.GraphicsEnvironment;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest
{
    @Test
    void testConstructor_ShouldCreateMenus()
    {
        Assumptions.assumeFalse(GraphicsEnvironment.isHeadless());
        MenuController menu = new MenuController(new PresentationActions(null, Presentation.getInstance()));

        assertEquals(3, menu.getMenuCount());
    }
}