package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresentationBuilderVisitorTest {
    private PresentationBuilderVisitor visitor;
    private Presentation presentation;

    @BeforeEach
    void setup() {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
        visitor = new PresentationBuilderVisitor(presentation, new DefaultSlideItemFactory());
    }

    @Test
    void testVisitPresentationNode_ShouldSetPresentationTitle() {
        PresentationNode node = new PresentationNode();
        node.title = "My Presentation";

        visitor.visit(node);
        assertEquals("My Presentation", presentation.getTitle());
    }

    @Test
    void testVisitSlideNode_ShouldAddNewSlideWithTitle() {
        SlideNode node = new SlideNode();
        node.title = "Slide One";

        visitor.visit(node);

        assertEquals(1, presentation.getSlides().size());
        assertEquals("Slide One", presentation.getSlides().get(0).getTitle());
    }

    @Test
    void testVisitTitleNode_WithActiveSlide_ShouldOverrideSlideTitle() {
        // Create an active slide first
        SlideNode slideNode = new SlideNode();
        visitor.visit(slideNode);

        // Update the title using TitleNode
        TitleNode titleNode = new TitleNode();
        titleNode.text = "Updated Title";
        visitor.visit(titleNode);

        assertEquals("Updated Title", presentation.getSlides().get(0).getTitle());
    }

    @Test
    void testVisitItemNode_ValidTextItem_ShouldAddSlideItem() {
        // Create an active slide first
        SlideNode slideNode = new SlideNode();
        visitor.visit(slideNode);

        // Add an item node
        ItemNode itemNode = new ItemNode();
        itemNode.kind = "text";
        itemNode.level = 2;
        itemNode.text = "Hello World";
        visitor.visit(itemNode);

        Slide slide = presentation.getSlides().get(0);
        assertEquals(1, slide.getSlideItems().size());
        assertEquals(2, slide.getSlideItems().get(0).getLevel());
        assertEquals("Hello World", slide.getSlideItems().get(0).getText());
    }
}