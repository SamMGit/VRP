import java.util.Set;

public class Main {

  public static void main(String[] args) {

    // Build a loadSet from the file passed in.
    Set<Load> loadSet = FileHandler.buildLoadSet(args[0]);

    // Create the first driver.
    Driver currentDriver = new Driver();

    // While there are still loads to be delivered find loads for the driver.
    while (!loadSet.isEmpty()) {

      // Find a load for the driver.
      Load load = currentDriver.findLoad(loadSet);

      // If there is a load the driver can deliver remove the load from the load set.
      if (load != null) {
        loadSet.remove(load);
      } else {
        // If there are no more loads the driver can deliver print out the driver's loads and create a new driver.
        currentDriver.print();
        currentDriver = new Driver();
      }

    }

    // Once all loads have been delivered print out the last driver's loads.
    currentDriver.print();

  }

}
