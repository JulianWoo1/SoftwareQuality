package com.nhlstenden.jabberpoint.SlideItems;

import com.nhlstenden.jabberpoint.SlideItem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BitmapItem extends SlideItem
{
    private BufferedImage bufferedImage;
    private String filePath;
    
    public BitmapItem(int level, String filePath) throws IOException
    {
        super(level);
        
        this.filePath = filePath;
        this.bufferedImage = ImageIO.read(new File(filePath));
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public void setFilePath(String filePath) throws IOException
    {
        this.filePath = filePath;
        this.bufferedImage = ImageIO.read(new File(filePath));
    }

    @Override
    public void draw(Graphics graphics, int x, int y)
    {
        if(this.bufferedImage == null)
        {
            return;
        }

        int indent = this.level * 20;

        graphics.drawImage(this.bufferedImage, x + indent, y, null);
    }
}
