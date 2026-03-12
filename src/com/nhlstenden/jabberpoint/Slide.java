package com.nhlstenden.jabberpoint;

import java.awt.*;
import java.util.List;

public class Slide implements SlideComponent
{
    private String title;
    private List<SlideItem> slideItems;

    public String getTitle()
    {
        return this.title;
    }

    public List<SlideItem> getSlideItems()
    {
        return this.slideItems;
    }

    @Override
    public void draw(Graphics graphics, int x, int y)
    {

    }
}
