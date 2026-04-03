package com.nhlstenden.jabberpoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slide implements SlideComponent
{
    private String title;
    private final List<SlideItem> slideItems = new ArrayList<>();

    public String getTitle()
    {
        return this.title;
    }

    public List<SlideItem> getSlideItems()
    {
        return this.slideItems;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public void addSlideItem(SlideItem item) {
        this.slideItems.add(item);
    }

    @Override
    public void draw(Graphics graphics, int x, int y)
    {
        int offsetY = y + 50;

        graphics.setFont(new Font("Arial", Font.BOLD, 36));
        graphics.drawString(this.title, x + 50, offsetY);

        offsetY += 60;

        for(SlideItem item : this.slideItems)
        {
            item.draw(graphics, x + 70, offsetY);
            offsetY += 40;
        }
    }
}
