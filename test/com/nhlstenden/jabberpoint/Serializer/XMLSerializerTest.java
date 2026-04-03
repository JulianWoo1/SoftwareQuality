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
    void testSaveAndLoad_SlideWithTextItem_ShouldRestoreItemLevelAndContent() throws Exception
    {
        Slide slide = new Slide();
        slide.setTitle("slide with item");
        slide.addSlideItem(new TextItem(2, "Hello World"));
        presentation.getSlides().add(slide);
        presentation.setTitle("with-item");

        String path = "test_item.xml";
        serializer.saveFromPresentation(path, presentation);
        presentation.clearPresentation();
        serializer.loadToPresentation(path, presentation);

        Slide loaded = presentation.getSlides().get(0);
        assertEquals(1, loaded.getSlideItems().size());
        assertEquals("Hello World", loaded.getSlideItems().get(0).getText());
        assertEquals(2, loaded.getSlideItems().get(0).getLevel());
        new File(path).delete();
    }

    @Test
    void testSaveAndLoad_MultipleSlides_ShouldRestoreAll() throws Exception
    {
        for (int i = 0; i < 3; i++)
        {
            Slide slide = new Slide();
            slide.setTitle("Slide " + i);
            presentation.getSlides().add(slide);
        }
        presentation.setTitle("multi");

        String path = "test_multi.xml";
        serializer.saveFromPresentation(path, presentation);
        presentation.clearPresentation();
        serializer.loadToPresentation(path, presentation);

        assertEquals(3, presentation.getSlides().size());
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