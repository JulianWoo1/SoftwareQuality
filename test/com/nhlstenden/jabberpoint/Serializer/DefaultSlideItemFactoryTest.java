package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.SlideItem;
import com.nhlstenden.jabberpoint.SlideItems.BitmapItem;
import com.nhlstenden.jabberpoint.SlideItems.TextItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultSlideItemFactoryTest {
    private DefaultSlideItemFactory defaultSlideItemFactory;

    @BeforeEach
    void setup() {
        defaultSlideItemFactory = new DefaultSlideItemFactory();
    }

    @Test
    void testCreate_CreateTextItem_ShouldReturnTextItem() {
        SlideItem item = defaultSlideItemFactory.create("text", 1, "test");

        assertInstanceOf(TextItem.class, item);
    }

    @Test
    void testCreate_CreateImageItem_WithMissingFile_ShouldThrowException() {
        String invalidPath = "this_file_definitely_does_not_exist.png";

        assertThrows(RuntimeException.class, () -> {
            defaultSlideItemFactory.create("image", 1, invalidPath);
        });
    }
}