package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;
import com.nhlstenden.jabberpoint.Serializer.PresentationSerializer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Presentation implements SlideComponent
{
    private PresentationSerializer serializer;

    // D (Dependency Inversion Principle):
    // The Presentation class depends on the abstraction (PresentationSerializer)
    // instead of a concrete implementation.
    // This allows different serializers to be used interchangeably.
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
    private final List<PresentationChangeListener> changeListeners = new ArrayList<>();
    private PresentationSerializer serializer;

    private Presentation()
    {
        this.serializer = new XMLSerializer();
    }

    public void setSerializer(PresentationSerializer serializer)
    {
        this.serializer = serializer;
    }

    public void addChangeListener(PresentationChangeListener listener)
    {
        this.changeListeners.add(listener);
    }

    private void notifyChanged()
    {
        for (PresentationChangeListener listener : this.changeListeners)
        {
            listener.onPresentationChanged();
        }
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
        notifyChanged();
    }

    public int getCurrentSlide()
    {
        return this.currentSlide;
    }

    public void setCurrentSlide(int currentSlide)
    {
        if (currentSlide >= this.slides.size() || currentSlide < 0)
        {
            return;
        }
        this.currentSlide = currentSlide;
        notifyChanged();
    }

    public void nextSlide()
    {
        if (this.currentSlide + 1 >= this.slides.size())
        {
            return;
        }

        this.currentSlide++;
        notifyChanged();
    }

    public void previousSlide()
    {
        if (this.currentSlide - 1 < 0)
        {
            return;
        }
        this.currentSlide--;
        notifyChanged();
    }

    public void loadPresentationFromXMLFile(String path)
    {
        clearPresentation();

        try
        {
            this.serializer.loadToPresentation(path, this);
        } catch (Exception e)
        {
            System.out.printf("Could not load presentation \"%s\": %s%n", path, e.getMessage());
        }

        if (!this.slides.isEmpty())
        {
            setCurrentSlide(0);
        }
    }

    public void savePresentationToXMLFile(String path) 
    {
        try
        {
            this.serializer.saveFromPresentation(path, this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearPresentation()
    {
        this.currentSlide = 0;
        this.slides.clear();
        this.title = "Unknown presentation";
        notifyChanged();
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
