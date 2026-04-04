package com.nhlstenden.jabberpoint.Serializer;

import org.w3c.dom.Element;

public interface XMLVisitor
{
    void visitPresentation(Element element);

    void visitShowTitle(Element element);

    void visitSlide(Element element);

    void visitTitle(Element element);

    void visitItem(Element element);
}