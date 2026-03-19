package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.SlideItems.BitmapItem;
import com.nhlstenden.jabberpoint.SlideItems.TextItem;
import org.w3c.dom.Element;

import java.io.IOException;

public class SlideLoaderVisitor implements XMLVisitor {
    
    private Slide activeSlide;

    @Override
    public void visit(Element element)
    {
        Presentation presentation = Presentation.getInstance();
        String tag = element.getTagName();
        
        switch (tag)
        {
            case "showtitle": {
                presentation.setTitle(element.getTextContent());
                break;
            }
            case "slide": {
                this.activeSlide = new Slide();
                Presentation.getInstance().getSlides().add(this.activeSlide);
                break;
            }
            case "title":
            {
                assert this.activeSlide != null;
                this.activeSlide.setTitle(element.getTextContent());
                break;
            }
            case "item":
            {
                assert this.activeSlide != null;
                
                loadSlideItem(element);
                break;
            }
        }
    }

    private void loadSlideItem(Element element)
    {
        String kind = element.getAttribute("kind");
        int level = Integer.parseInt(element.getAttribute("level"));
        String content = element.getTextContent();
        
        switch (kind)
        {
            case "text":
            {
                this.activeSlide.addSlideItem(new TextItem(level, content));
                break;
            }
            
            case "image": {
                try
                {
                    this.activeSlide.addSlideItem(new BitmapItem(level, content));
                } catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
                break;
            }
        }
    }
}