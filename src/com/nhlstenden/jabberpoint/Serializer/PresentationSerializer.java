package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;

public interface PresentationSerializer
{
    void loadToPresentation(String path, Presentation presentation) throws Exception;

    void saveFromPresentation(String path, Presentation presentation) throws Exception;
}
