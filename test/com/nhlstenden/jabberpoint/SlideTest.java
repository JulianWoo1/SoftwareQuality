package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.SlideItems.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SlideTest
{
    private Slide slide;

    @BeforeEach
    void setup()
    {
        slide = new Slide();
    }

    @Test
    void testAddSlideItem_MultipleItems_ShouldRetainOrder()
    {
        slide.addSlideItem(new TextItem(1, "First"));
        slide.addSlideItem(new TextItem(2, "Second"));

        List<SlideItem> items = slide.getSlideItems();
        assertEquals("First", items.get(0).getText());
        assertEquals("Second", items.get(1).getText());
    }

    @Test
    void testDraw_HasTitleAndItems_ShouldNotThrow()
    {
        slide.setTitle("test title");
        slide.addSlideItem(new TextItem(1, "item"));

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        assertDoesNotThrow(()-> slide.draw(graphics, 0, 0));
        graphics.dispose();
    }

    @Test
    void testDraw_NoItems_ShouldNotThrow()
    {
        slide.setTitle("Empty Slide");

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        assertDoesNotThrow(() -> slide.draw(graphics, 0, 0));
        graphics.dispose();

    }
}