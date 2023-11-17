package eu.kocko.phototools.datastructures;

public class Trie {
  private Node root;

  public Trie(String rootName) {
    root = new Node(rootName);
  }

  public Node getRoot() {
    return root;
  }

}
