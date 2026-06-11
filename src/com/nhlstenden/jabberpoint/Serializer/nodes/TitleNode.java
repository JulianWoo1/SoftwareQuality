package com.nhlstenden.jabberpoint.Serializer.nodes;


import com.nhlstenden.jabberpoint.Serializer.NodeVisitor;

public class TitleNode implements Node
{
    public String text;

    @Override
    public void accept(NodeVisitor visitor)
    {
        visitor.visitTitle(this);
    }
}