package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import org.w3c.dom.Element;

public class SlideLoaderVisitor implements XMLVisitor
{
    private final Presentation presentation;
    private final SlideItemFactory slideItemFactory;
    private Slide activeSlide;

    public SlideLoaderVisitor(Presentation presentation, SlideItemFactory slideItemFactory)
    {
        this.presentation = presentation;
        this.slideItemFactory = slideItemFactory;
    }

    @Override
    public void visitPresentation(Element element)
    {
        this.activeSlide = null;
    }

    @Override
    public void visitShowTitle(Element element)
    {
        this.presentation.setTitle(element.getTextContent());
    }

    @Override
    public void visitSlide(Element element)
    {
        this.activeSlide = new Slide();
        this.presentation.getSlides().add(this.activeSlide);
    }

    @Override
    public void visitTitle(Element element)
    {
        if (this.activeSlide != null)
        {
            this.activeSlide.setTitle(element.getTextContent());
        }
    }

    @Override
    public void visitItem(Element element)
    {
        if (this.activeSlide != null)
        {
            loadSlideItem(element);
        }
    }

    private void loadSlideItem(Element element)
    {
        String kind = element.getAttribute("kind");
        int level = Integer.parseInt(element.getAttribute("level"));
        String content = element.getTextContent();

        this.activeSlide.addSlideItem(this.slideItemFactory.create(kind, level, content));
    }
}