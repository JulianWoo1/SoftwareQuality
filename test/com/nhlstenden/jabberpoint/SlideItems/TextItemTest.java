package com.nhlstenden.jabberpoint.SlideItems;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class TextItemTest
{
    private Graphics graphics()
    {
        return new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB).getGraphics();
    }

    @Test
    void testDraw_LevelZero_ShouldNotThrow()
    {
        TextItem item = new TextItem(0, "Hello");
        Graphics g = graphics();
        assertDoesNotThrow(() -> item.draw(g, 10, 10));
        g.dispose();
    }

    @Test
    void testDraw_HighLevel_ShouldApplyIndentWithoutThrowing()
    {
        TextItem item = new TextItem(5, "Indented");
        Graphics g = graphics();
        assertDoesNotThrow(() -> item.draw(g, 10, 10));
        g.dispose();
    }

    @Test
    void testGetKind_ShouldReturnText()
    {
        assertEquals("text", new TextItem(1, "x").getKind());
    }
}