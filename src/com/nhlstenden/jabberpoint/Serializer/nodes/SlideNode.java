package com.nhlstenden.jabberpoint.Serializer.nodes;

import com.nhlstenden.jabberpoint.Serializer.NodeVisitor;
import java.util.ArrayList;
import java.util.List;

public class SlideNode implements Node {
  public String title;
  public List<Node> children = new ArrayList<>();

  @Override
  public void accept(NodeVisitor visitor) {
    visitor.visitSlide(this);

    for (Node child : children) {
      child.accept(visitor);
    }
  }
}
