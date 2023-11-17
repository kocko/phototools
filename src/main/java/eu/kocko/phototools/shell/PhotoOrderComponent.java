package eu.kocko.phototools.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@ShellComponent
public class PhotoOrderComponent {

  @ShellMethod(value = "Rename a set of pictures to some common title and reorder them by timestamp (Creation time).")
  public String reorder(@ShellOption(value = "-d") String directory,
                        @ShellOption(value = "-t") String title) {
    File[] files = new File(directory).listFiles();
    List<File> images = new ArrayList<>();
    for (File file : files) {
      if (file.isFile()) {
        try {
          String mimetype = Files.probeContentType(file.toPath());
          if (mimetype != null && mimetype.split("/")[0].equals("image")) {
            images.add(file);
          }
        } catch (IOException ex) {
          throw new IllegalArgumentException(ex);
        }
      }
    }
    images.sort(Comparator.comparingLong(image -> image.lastModified()));
    int counter = 1;

    for (File image : images) {
      String extension = getExtension(image.getName());
      image.renameTo(new File(image.getParent() + "/" + nameFrom(title, counter++) + extension));
    }
    return "Done";
  }

  private String nameFrom(String description, int counter) {
    return String.format("%s_%04d", description, counter);
  }

  private String getExtension(String name) {
    return name.substring(name.indexOf("."));
  }

}
