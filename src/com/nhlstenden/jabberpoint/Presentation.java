package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Presentation implements SlideComponent
{
    private static Presentation instance;
    
    public static Presentation getInstance() {
        if(instance == null)
        {
            instance = new Presentation();
        }
        
        return instance;
    }
    
    private String title;
    private List<Slide> slides;
    private int currentSlide;
    
    private Presentation(){}

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public List<Slide> getSlides()
    {
        return this.slides;
    }

    public void setSlides(List<Slide> slides)
    {
        this.slides = slides;
    }

    public int getCurrentSlide()
    {
        return this.currentSlide;
    }

    public void setCurrentSlide(int currentSlide)
    {
        this.currentSlide = currentSlide;
    }
    
    public void nextSlide() {
        if(this.currentSlide >= this.slides.size()) return;
        
        this.currentSlide++;
    }
    
    public void previousSlide() {
        if(this.currentSlide <= 0) return;
        this.currentSlide--;
    }
    
    public void loadPresentationFromXMLFile(String path) {
        
    }
    
    public void savePresentationToXMLFile(String path) {
        XMLSerializer serializer = new XMLSerializer();
        try
        {
            serializer.load(path);
            loadPresentation(serializer.getTitle(), serializer.getSlides());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    public void loadPresentation(String title, List<Slide> slides) {
        this.title = title;
        this.slides = slides;
        this.currentSlide = 0;
    }
    

    @Override
    public void draw(Graphics graphics, int x, int y)
    {
        
    }
}
