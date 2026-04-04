package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class SlideLoaderVisitorTest
{
    private SlideLoaderVisitor visitor;
    private Presentation presentation;

    @BeforeEach
    void setup()
    {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
        visitor = new SlideLoaderVisitor(presentation, new DefaultSlideItemFactory());
    }

    private Element parseElement(String xml) throws Exception
    {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        return doc.getDocumentElement();
    }

    @Test
    void testVisitSlide_ShouldAddNewSlide() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        assertEquals(1, presentation.getSlides().size());
    }

    @Test
    void testVisitSlide_TwoCalls_ShouldAddTwoSlides() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        visitor.visitSlide(parseElement("<slide/>"));
        assertEquals(2, presentation.getSlides().size());
    }

    @Test
    void testVisitShowTitle_ShouldSetPresentationTitle() throws Exception
    {
        visitor.visitShowTitle(parseElement("<showtitle>My Presentation</showtitle>"));
        assertEquals("My Presentation", presentation.getTitle());
    }

    @Test
    void testVisitTitle_WithActiveSlide_ShouldSetSlideTitle() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        visitor.visitTitle(parseElement("<title>Slide One</title>"));
        assertEquals("Slide One", presentation.getSlides().get(0).getTitle());
    }

    @Test
    void testVisitTitle_WithoutActiveSlide_ShouldNotThrow() throws Exception
    {
        visitor.visitPresentation(parseElement("<presentation/>"));
        assertDoesNotThrow(() -> visitor.visitTitle(parseElement("<title>No Slide</title>")));
    }

    @Test
    void testVisitItem_ValidXML_ShouldAddSlideItem() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        visitor.visitItem(parseElement("<item kind=\"text\" level=\"1\">Hello</item>"));
        assertEquals(1, presentation.getSlides().get(0).getSlideItems().size());
    }

    @Test
    void testVisitItem_ShouldHaveCorrectLevelAndContent() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        visitor.visitItem(parseElement("<item kind=\"text\" level=\"3\">Detail text</item>"));

        Slide slide = presentation.getSlides().get(0);
        assertEquals(3, slide.getSlideItems().get(0).getLevel());
        assertEquals("Detail text", slide.getSlideItems().get(0).getText());
    }

    @Test
    void testVisitItem_WithoutActiveSlide_ShouldNotAddItem() throws Exception
    {
        visitor.visitPresentation(parseElement("<presentation/>"));
        visitor.visitItem(parseElement("<item kind=\"text\" level=\"1\">Orphan</item>"));
        assertTrue(presentation.getSlides().isEmpty());
    }

    @Test
    void testVisitPresentation_ShouldResetActiveSlide() throws Exception
    {
        visitor.visitSlide(parseElement("<slide/>"));
        visitor.visitPresentation(parseElement("<presentation/>"));

        visitor.visitTitle(parseElement("<title>Should not be set</title>"));

        assertNull(presentation.getSlides().get(0).getTitle());
    }
}