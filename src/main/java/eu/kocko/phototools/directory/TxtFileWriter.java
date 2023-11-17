package eu.kocko.phototools.directory;

import eu.kocko.phototools.datastructures.Node;
import eu.kocko.phototools.datastructures.Trie;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

@Component
public class TxtFileWriter implements FileWriter{

  @Override
  public FileType getType() {
    return FileType.TXT;
  }

  @Override
  public String getSeparator() {
    return "  ";
  }

  @Override
  public void writeToFile(Trie trie, String filename) {
    File file = new File(filename);
    try (PrintWriter out = new PrintWriter(file)) {
      write(out, trie.getRoot(), 1, "1");
      out.flush();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  private void write(PrintWriter out, Node node, int level, String order) {
    for (int i = 1; i < level; i++) {
      out.print(getSeparator());
    }
    out.print(order + ") ");
    out.println(node.getName());
    int nextOrder = 1;
    for (String childName : node.getChildKeys()) {
      write(out, node.getChild(childName), level + 1, order + "." + nextOrder);
      nextOrder++;
    }
  }

}
