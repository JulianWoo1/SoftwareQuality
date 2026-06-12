package com.nhlstenden.jabberpoint.Controllers;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationActionsTest {
    private Presentation presentation;
    private PresentationActions actions;

    @BeforeEach
    void setup() {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();

        PresentationService service = new PresentationService(presentation, new XMLSerializer(), null);
        actions = new PresentationActions(service);
    }

    @Test
    void testNewPresentation_ShouldClearSlides() {
        presentation.getSlides().add(new Slide());
        presentation.setTitle("something");

        actions.newPresentation();

        assertTrue(presentation.getSlides().isEmpty());
        assertEquals("Unknown presentation", presentation.getTitle());
    }

    @Test
    void testNextSlide_ShouldAdvanceCurrentSlide() {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());

        actions.nextSlide();

        assertEquals(1, presentation.getCurrentSlide());
    }

    @Test
    void testPreviousSlide_ShouldGoBackCurrentSlide() {
        presentation.getSlides().add(new Slide());
        presentation.getSlides().add(new Slide());
        presentation.setCurrentSlide(1);

        actions.previousSlide();

        assertEquals(0, presentation.getCurrentSlide());
    }
}