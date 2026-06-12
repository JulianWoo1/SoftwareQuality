package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;

public abstract class NodeVisitorAdapter implements NodeVisitor {

    @Override
    public void visit(PresentationNode node) {
    }

    @Override
    public void visit(SlideNode node) {
    }

    @Override
    public void visit(TitleNode node) {
    }

    @Override
    public void visit(ItemNode node) {
    }
}