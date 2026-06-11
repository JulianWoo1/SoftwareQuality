package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;

public interface NodeVisitor {
  void visitPresentation(PresentationNode node);

  void visitSlide(SlideNode node);

  void visitTitle(TitleNode node);

  void visitItem(ItemNode node);
}
