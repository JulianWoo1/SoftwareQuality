package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{
    private  Presentation presentation;

    @BeforeEach
    void setup()
    {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
    }

    @Test
    void testSetCurrentSlide_ValidIndex_ShouldReturnOne()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testSetCurrentSlide_InvalidIndex_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(0);

        assertEquals(0, presentation.getCurrentSlide());
    }
    @Test
    void testSetCurrentSlide_NegativeIndex_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(0);

        presentation.setCurrentSlide(-1);

        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testNextSlide_CurrentSlideZero_ShouldReturnOne()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(0);
        presentation.nextSlide();

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testNextSlide_CurrentSlideIsLastSlide_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);
        presentation.nextSlide();

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testPreviousSlide_CurrentSlideIsOne_ShouldReturnZero()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);
        presentation.previousSlide();

        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testPreviousSlide_CurrentSlideIsFirstSlide_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(0);
        presentation.previousSlide();

        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testClearPresentation_PresentationCleared_SlidesShouldBeEmptyTitleShouldBeUnknownPresentation()
    {
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(1);
        presentation.setTitle("test");

        presentation.clearPresentation();

        assertEquals(0, presentation.getCurrentSlide());
        assertTrue(presentation.getSlides().isEmpty());
        assertEquals("Unknown presentation", presentation.getTitle());
    }

    @Test
    void testSingleton_SameInstances_ShouldReturnSameInstance()
    {
        Presentation presentation1 = Presentation.getInstance();
        Presentation presentation2 = Presentation.getInstance();

        assertSame(presentation1, presentation2);
    }

    @Test
    void testDraw_HasSlide_ShouldNotThrow()
    {
        Slide slide = new Slide();
        slide.setTitle("Test");
        presentation.getSlides().add(slide);

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        assertDoesNotThrow(() -> presentation.draw(g, 0, 0));
        g.dispose();
    }

    @Test
    void testDraw_NoSlides_ShouldNotThrow()
    {
        BufferedImage image = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        assertDoesNotThrow(() -> presentation.draw(g, 0, 0));
        g.dispose();
    }

    @Test
    void testLoadPresentationFromXMLFile_NoFile_ShouldNotThrow()
    {
        assertDoesNotThrow(() ->
                presentation.loadPresentationFromXMLFile("NoFile.xml")
        );
    }

    @Test
    void testSavePresentationToXMLFile_PathIsValid_ShouldNotThrow()
    {
        presentation.setTitle("test");
        Slide slide = new Slide();
        slide.setTitle("test");
        presentation.getSlides().add(slide);

        String path = "test.xml";
        assertDoesNotThrow(() -> presentation.savePresentationToXMLFile(path));
        new java.io.File(path).delete();
    }
}