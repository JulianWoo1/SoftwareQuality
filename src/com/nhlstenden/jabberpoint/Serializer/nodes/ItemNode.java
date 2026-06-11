package com.nhlstenden.jabberpoint.Serializer.nodes;


import com.nhlstenden.jabberpoint.Serializer.NodeVisitor;

public class ItemNode implements Node
{
    public String text;
    public int level;
    public String kind;

    @Override
    public void accept(NodeVisitor visitor)
    {
        visitor.visitItem(this);
    }
}