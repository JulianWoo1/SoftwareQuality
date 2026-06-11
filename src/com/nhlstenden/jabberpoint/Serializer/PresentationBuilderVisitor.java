package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.*;
import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;

public class PresentationBuilderVisitor implements NodeVisitor {
  private final Presentation presentation;
  private final DefaultSlideItemFactory factory;

  private Slide currentSlide;

  public PresentationBuilderVisitor(Presentation presentation, DefaultSlideItemFactory factory) {
    this.presentation = presentation;
    this.factory = factory;
  }

  @Override
  public void visitPresentation(PresentationNode node) {
    presentation.setTitle(node.title);
  }

  @Override
  public void visitSlide(SlideNode node) {
    currentSlide = new Slide();
    currentSlide.setTitle(node.title);
    presentation.addSlide(currentSlide);
  }

  @Override
  public void visitTitle(TitleNode node) {
    if (currentSlide != null) {
      currentSlide.setTitle(node.text);
    }
  }

  @Override
  public void visitItem(ItemNode node) {
    if (currentSlide != null) {
      currentSlide.addSlideItem(factory.create(node.kind, node.level, node.text));
    }
  }
}
