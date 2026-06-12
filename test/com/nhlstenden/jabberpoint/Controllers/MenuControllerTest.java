package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {
    @Test
    void testConstructor_ShouldCreateMenus() {
        Assumptions.assumeFalse(GraphicsEnvironment.isHeadless());

        Presentation presentation = Presentation.getInstance();
        PresentationService service = new PresentationService(presentation, new XMLSerializer(), null);
        PresentationActions actions = new PresentationActions(service);

        MenuController menu = new MenuController(actions);

        assertEquals(3, menu.getMenuCount());
    }
}