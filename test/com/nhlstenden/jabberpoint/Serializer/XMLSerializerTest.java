package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.SlideItems.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class XMLSerializerTest
{
    private XMLSerializer serializer;
    private Presentation presentation;
    private Slide slide;

    @BeforeEach
    void setup()
    {
        serializer = new XMLSerializer();
        slide = new Slide();
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
    }

    @Test
    void testSaveAndLoad_ShouldReturnFalseShouldReturnDemo() throws Exception
    {
        presentation.clearPresentation();

        slide.setTitle("test slide");
        presentation.getSlides().add(slide);
        presentation.setTitle("demo");

        String path = "test.xml";
        serializer.saveFromPresentation(path, presentation);
        Presentation newPresentation = Presentation.getInstance();
        newPresentation.clearPresentation();

        serializer.loadToPresentation(path, newPresentation);
        assertFalse(newPresentation.getSlides().isEmpty());
        assertEquals("demo", presentation.getTitle());
        new File(path).delete();
    }

    @Test
    void testLoadToPresentation_NonExistentFile_ShouldThrow()
    {
        assertThrows(Exception.class, () ->
                serializer.loadToPresentation("nonexistent_file.xml", presentation)
        );
    }
}