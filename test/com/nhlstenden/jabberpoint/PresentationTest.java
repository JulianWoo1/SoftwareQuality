package com.nhlstenden.jabberpoint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PresentationTest
{
    private Presentation presentation;

    @BeforeEach
    void setup()
    {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
    }

    @Test
    void testSetCurrentSlide_ValidIndex_ShouldChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testSetCurrentSlide_IndexEqualToSize_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(1);
        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testSetCurrentSlide_NegativeIndex_ShouldNotChange()
    {
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(-1);
        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testNextSlide_ShouldAdvance()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.nextSlide();

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testNextSlide_OnLastSlide_ShouldNotAdvance()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);
        presentation.nextSlide();

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testPreviousSlide_ShouldGoBack()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        presentation.setCurrentSlide(1);
        presentation.previousSlide();

        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testPreviousSlide_OnFirstSlide_ShouldNotGoBack()
    {
        presentation.getSlides().add(new Slide());
        presentation.previousSlide();
        assertEquals(0, presentation.getCurrentSlide());
    }

    @Test
    void testClearPresentation_ShouldResetSlidesTitleAndIndex()
    {
        presentation.getSlides().add(new Slide());
        presentation.setTitle("test");

        presentation.clearPresentation();

        assertEquals(0, presentation.getCurrentSlide());
        assertTrue(presentation.getSlides().isEmpty());
        assertEquals("Unknown presentation", presentation.getTitle());
    }

    @Test
    void testSingleton_ShouldReturnSameInstance()
    {
        assertSame(Presentation.getInstance(), Presentation.getInstance());
    }

    @Test
    void testSetSlides_ShouldReplaceExistingSlides()
    {
        presentation.getSlides().add(new Slide());

        ArrayList<Slide> newSlides = new ArrayList<>();
        newSlides.add(new Slide());
        newSlides.add(new Slide());
        presentation.setSlides(newSlides);

        assertEquals(2, presentation.getSlides().size());
    }

    @Test
    void testChangeListener_NotifiedOnNextSlide()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        boolean[] notified = {false};
        presentation.addChangeListener(() -> notified[0] = true);
        presentation.nextSlide();

        assertTrue(notified[0]);
    }

    @Test
    void testChangeListener_NotifiedOnPreviousSlide()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(1);

        boolean[] notified = {false};
        presentation.addChangeListener(() -> notified[0] = true);
        presentation.previousSlide();

        assertTrue(notified[0]);
    }

    @Test
    void testChangeListener_NotifiedOnClear()
    {
        boolean[] notified = {false};
        presentation.addChangeListener(() -> notified[0] = true);
        presentation.clearPresentation();

        assertTrue(notified[0]);
    }

    @Test
    void testChangeListener_NotifiedOnSetSlides()
    {
        boolean[] notified = {false};
        presentation.addChangeListener(() -> notified[0] = true);
        presentation.setSlides(new ArrayList<>());

        assertTrue(notified[0]);
    }

    @Test
    void testChangeListener_NotifiedOnSetCurrentSlide()
    {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        boolean[] notified = {false};
        presentation.addChangeListener(() -> notified[0] = true);
        presentation.setCurrentSlide(1);

        assertTrue(notified[0]);
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