package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.SlideItem;
import com.nhlstenden.jabberpoint.SlideItems.BitmapItem;
import com.nhlstenden.jabberpoint.SlideItems.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSlideItemFactoryTest
{
    private DefaultSlideItemFactory defaultSlideItemFactory;
    
    @BeforeEach
    void setup()
    {
        defaultSlideItemFactory = new DefaultSlideItemFactory();
    }

    @Test
    void testCreate_CreateTextItem_ShouldReturnTextItem()
    {
        SlideItem item = defaultSlideItemFactory.create("text", 1, "test");

        assertInstanceOf(TextItem.class, item);
    }

    @Test
    void testCreate_CreateImageItem_ShouldReturnBitmapItem()
    {
        String path = getClass().getResource("/test.jpg").getPath();
        SlideItem item = defaultSlideItemFactory.create("image", 1, path);

        assertInstanceOf(BitmapItem.class, item);
    }

    @Test
    void testCreate_UnsupportedType_ShouldThrow()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            defaultSlideItemFactory.create("unknown", 1, "data");
        });
    }

    @Test
    void testCreate_TextItem_ShouldEquelsLevelAndContent()
    {
        TextItem item = (TextItem) defaultSlideItemFactory.create("text", 2, "test");

        assertEquals(2, item.getLevel());
        assertEquals("test", item.getText());
    }

    @Test
    void testCreate_ImageItemHasInvalidPath_ShouldThrow()
    {
        assertThrows(RuntimeException.class, () -> {
            defaultSlideItemFactory.create("image", 1, "test.jpg");
        });
    }
}