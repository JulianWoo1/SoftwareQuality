package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class SlideLoaderVisitorTest
{
    private SlideItemFactory factory;
    private Presentation presentation;

    @BeforeEach
    void setup()
    {
        presentation = Presentation.getInstance();
        presentation.clearPresentation();
    }

    @Test
    void visitSlide_ShouldAddSlide()
    {

        SlideLoaderVisitor visitor = new SlideLoaderVisitor(presentation, new DefaultSlideItemFactory());

        visitor.visitSlide(null);

        assertEquals(1, presentation.getSlides().size());
    }

}