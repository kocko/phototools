package eu.kocko.phototools.shell;

import eu.kocko.phototools.datastructures.Trie;
import eu.kocko.phototools.directory.DirectoryTraverser;
import eu.kocko.phototools.directory.FileType;
import eu.kocko.phototools.directory.FileWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
public class ExpandComponent {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExpandComponent.class);

  private DirectoryTraverser traverser;
  private List<FileWriter> fileWriters;

  public ExpandComponent(DirectoryTraverser traverser, List<FileWriter> fileWriters) {
    this.traverser = traverser;
    this.fileWriters = fileWriters;
  }

  @ShellMethod(value = "expand a given directory's subdirectories to a file.")
  public String expand(@ShellOption(value = "-d") String directory,
                       @ShellOption(value = "-t") FileType fileType,
                       @ShellOption(value = "-n") String filename) {
    Trie trie = traverser.traverse(directory);

    for (FileWriter writer : fileWriters) {
      if (writer.getType() == fileType) {
        LOGGER.info("Exporting to a file {}.", filename);
        writer.writeToFile(trie, filename);
        return ("Successfully expanded the directory '" + directory + "' to file, located in '" + filename + "'.");
      }
    }
    return "Invalid filetype (" + fileType + ") or filename (" + filename + ").";
  }

  @ShellMethod
  public String pwd() {
    return System.getProperty("user.dir");
  }

}
