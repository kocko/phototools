package eu.kocko.phototools.directory;

import eu.kocko.phototools.datastructures.Node;
import eu.kocko.phototools.datastructures.Trie;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

@Component
public class CsvFileWriter implements FileWriter {

  @Override
  public FileType getType() {
    return FileType.CSV;
  }

  @Override
  public String getSeparator() {
    return ",";
  }

  @Override
  public void writeToFile(Trie trie, String filename) {
    File file = new File(filename);
    try (PrintWriter out = new PrintWriter(file)) {
      write(out, trie.getRoot(), 0);
      out.flush();
    } catch (FileNotFoundException ex) {
      throw new IllegalArgumentException(ex);
    }
  }

  private void write(PrintWriter out, Node node, int level) {
    for (int i = 0; i < level; i++) {
      out.print(getSeparator());
    }
    String name = node.getName();
    if (name.contains(getSeparator())) {
      name = "\"" + name + "\"";
    }
    out.println(name);
    for (String childName : node.getChildKeys()) {
      write(out, node.getChild(childName), level + 1);
    }
  }

}
