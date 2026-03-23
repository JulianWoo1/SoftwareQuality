package com.nhlstenden.jabberpoint.SlideItems;

import com.nhlstenden.jabberpoint.SlideItem;

import java.awt.*;

public class TextItem extends SlideItem
{
    private String textValue;
    
    public TextItem(int level, String textValue) {
        super(level);
        this.textValue = textValue;
    }

    public String getTextValue()
    {
        return this.textValue;
    }

    public void setTextValue(String textValue)
    {
        this.textValue = textValue;
    }

    @Override
    public void draw(Graphics graphics, int x, int y)
    {
        int indent = this.level * 20;

        graphics.setFont(new Font("Arial", Font.PLAIN, 20));
        graphics.drawString(this.textValue, x + indent, y);
    }

    @Override
    public String getText()
    {
        return this.textValue;
    }
}
