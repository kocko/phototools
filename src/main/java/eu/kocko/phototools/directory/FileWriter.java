package eu.kocko.phototools.directory;

import eu.kocko.phototools.datastructures.Trie;

public interface FileWriter {

  void writeToFile(Trie trie, String filename);

  FileType getType();

  String getSeparator();
}
