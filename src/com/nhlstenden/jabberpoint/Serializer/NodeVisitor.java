package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;

public interface NodeVisitor {
  void visit(PresentationNode node);

  void visit(SlideNode node);

  void visit(TitleNode node);

  void visit(ItemNode node);
}
