package eu.kocko.phototools.directory;

import eu.kocko.phototools.datastructures.Node;
import eu.kocko.phototools.datastructures.Trie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.management.NotificationEmitter;
import java.io.*;
import java.util.*;

import static java.lang.Math.*;

@Component
public class DirectoryTraverser {

  private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryTraverser.class);

  public Trie traverse(String folder) {
    File root = new File(folder);
    if (!root.isDirectory()) {
      LOGGER.error("{} is not a valid directory.", folder);
      throw new IllegalArgumentException("Not a directory");
    }
    trie = new Trie(root.getName());
    recurse(root, trie.getRoot());
    return trie;
  }

  private Trie trie;

  private void recurse(File file, Node currentNode) {
    File[] children = file.listFiles();
    for (File child : children) {
      if (child.isDirectory()) {
        Node childNode = currentNode.getOrCreate(child.getName());
        recurse(child, childNode);
      }
    }
  }

}
