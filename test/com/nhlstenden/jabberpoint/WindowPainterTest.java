package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class WindowPainterTest
{
    private Presentation presentation;
    private WindowPainter windowPainter;

    @BeforeEach
    void setup()
    {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
        windowPainter = new WindowPainter(presentation);
    }

    @Test
    void testPresentationChanged_ShouldNotThrow()
    {
        assertDoesNotThrow(()-> windowPainter.onPresentationChanged());
    }

    @Test
    void testPaintComponent_EmptyPresentation_ShouldNotThrow()
    {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        assertDoesNotThrow(() -> windowPainter.paint(g));
        g.dispose();
    }

    @Test
    void testPaintComponent_WithSlide_ShouldNotThrow()
    {
        Slide slide = new Slide();
        slide.setTitle("Test");
        presentation.getSlides().add(slide);

        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        assertDoesNotThrow(() -> windowPainter.paint(g));
        g.dispose();
    }
}