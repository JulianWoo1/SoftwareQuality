package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.SlideItem;

public interface SlideItemFactory
{
    SlideItem create(String kind, int level, String content);
}
