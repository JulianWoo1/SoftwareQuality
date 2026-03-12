package com.nhlstenden.jabberpoint.Serializer;

import org.w3c.dom.Element;

public interface XMLVisitor {
    void visit(Element element);
}