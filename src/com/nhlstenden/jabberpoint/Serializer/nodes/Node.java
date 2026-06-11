package com.nhlstenden.jabberpoint.Serializer.nodes;

import com.nhlstenden.jabberpoint.Serializer.NodeVisitor;

public interface Node {
  void accept(NodeVisitor visitor);
}
