package eu.kocko.phototools.datastructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Node {
  private String name;
  private Map<String, Node> children;

  Node(String name) {
    this.name = name;
    this.children = new HashMap<>();
  }

  public Node getOrCreate(String name) {
    Node result = children.getOrDefault(name, new Node(name));
    children.put(name, result);
    return result;
  }

  public String getName() {
    return name;
  }

  public Set<String> getChildKeys() {
    return new TreeSet<>(children.keySet());
  }

  public Node getChild(String key) {
    return children.get(key);
  }

}
