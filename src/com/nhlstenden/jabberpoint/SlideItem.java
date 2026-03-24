package com.nhlstenden.jabberpoint;

public abstract class SlideItem implements SlideComponent
{
    protected int level = 0;

    public SlideItem(int level) {
        this.level = level;
    }
    
    public SlideItem() {
        this(0);
    }

    public int getLevel()
    {
        return this.level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public abstract String getText();

    public abstract String getKind();
}
