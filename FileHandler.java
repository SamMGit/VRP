import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FileHandler {

  /**
   * Creates the Set of Loads listed in the file passed in.
   *
   * @return the created Set of Loads.
   */
  public static Set<Load> buildLoadSet(String fileName) {

    File file = new File(fileName);

    Set<Load> loadSet = new HashSet<>();

    try {

      BufferedReader reader = new BufferedReader(new FileReader(file));
      // Read the first line in the file describing its format.
      reader.readLine();
      String line;
      while ((line = reader.readLine()) != null) {
        // For each line in the file build a Load and add it to the Set of Loads.
        loadSet.add(new Load.Builder().setRawString(line).build());
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return loadSet;

  }

}
