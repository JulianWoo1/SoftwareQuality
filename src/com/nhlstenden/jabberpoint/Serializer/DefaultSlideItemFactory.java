package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.SlideItem;
import com.nhlstenden.jabberpoint.SlideItems.BitmapItem;
import com.nhlstenden.jabberpoint.SlideItems.TextItem;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DefaultSlideItemFactory implements SlideItemFactory
{
    private interface ItemCreator
    {
        SlideItem create(int level, String content);
    }

    private final Map<String, ItemCreator> creators = new HashMap<>();

    public DefaultSlideItemFactory()
    {
        this.creators.put("text", (level, content) -> new TextItem(level, content));
        this.creators.put("image", (level, content) -> {
            try
            {
                return new BitmapItem(level, content);
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public SlideItem create(String kind, int level, String content)
    {
        ItemCreator creator = this.creators.get(kind);
        if (creator == null)
        {
            throw new IllegalArgumentException("Unsupported slide item kind: " + kind);
        }

        return creator.create(level, content);
    }
}
