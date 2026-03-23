package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Presentation implements SlideComponent
{
    private static volatile Presentation instance;

    public static Presentation getInstance()
    {
        if (instance == null)
        {
            synchronized (Presentation.class)
            {
                if (instance == null)
                {
                    instance = new Presentation();
                }
            }
        }
        return instance;
    }

    private String title;
    private List<Slide> slides = new ArrayList<>();
    private int currentSlide;

    private Presentation()
    {
    }

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
        if (currentSlide > this.slides.size() || currentSlide < 0)
        {
            return;
        }
        this.currentSlide = currentSlide;
        
        WindowPainter.DoRepaint();
    }

    public void nextSlide()
    {
        if (this.currentSlide + 1 > this.slides.size())
        {
            return;
        }

        this.currentSlide++;
        WindowPainter.DoRepaint();
    }

    public void previousSlide()
    {
        if (this.currentSlide - 1 < 0)
        {
            return;
        }
        this.currentSlide--;
        WindowPainter.DoRepaint();
    }

    public void loadPresentationFromXMLFile(String path)
    {
        clearPresentation();
        XMLSerializer serializer = new XMLSerializer();
        
        try
        {
            serializer.load(path);
        } catch (Exception e)
        {
            System.out.printf("Could not load presentation \"%s\": %s%n", path, e.getMessage());
        }
        setCurrentSlide(0);
    }

    public void savePresentationToXMLFile(String path) 
    {
        XMLSerializer serializer = new XMLSerializer();
        serializer.setTitle(this.title);

        for(Slide slide : this.slides)
        {
            serializer.addSlide(slide);
        }

        try
        {
            serializer.save(path);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearPresentation()
    {
        this.currentSlide = 0;
        this.slides.clear();
        this.title = "Unknown presentation";
        WindowPainter.DoRepaint();
    }


    @Override
    public void draw(Graphics graphics, int x, int y)
    {
        if (this.slides == null || this.slides.isEmpty())
        {
            graphics.drawString("No slides loaded", x + 50, y + 50);
            return;
        }

        Slide slide = this.slides.get(this.currentSlide);
        slide.draw(graphics, x, y);
    }
}
