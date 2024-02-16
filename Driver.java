import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Driver {

  private static final Location DEPOT_LOCATION = new Location(0, 0);
  private static final int MAX_DRIVE_MINUTES = 720;

  // List of load ids the driver has delivered.
  private final List<Integer> loadList;
  // The location of the driver.
  private Location curLocation;
  // The total drive time of the driver.
  private double time;

  /**
   * Creates a driver with an empty loadList, a starting location of the depot, and a total time of 0.
   */
  public Driver() {
    this.loadList = new ArrayList<>();
    this.curLocation = DEPOT_LOCATION;
    this.time = 0;
  }

  /**
   *
   * Finds a load in the loadSet that the driver can deliver and adds the load to the drivers loadList.
   *
   * @param loadSet the set of loads that still need to be delivered.
   * @return the load the driver can deliver if one was found and null otherwise.
   */
  public Load findLoad(Set<Load> loadSet) {

    Load result = null;
    double minTripTime = Double.MAX_VALUE;

    // Find the load with the minimum trip time.
    for (Load load : loadSet) {
      // Calculate the trip time starting at the driver's current location and ending at the load's end location.
      double tripTime = load.calculateTripTime(curLocation);
      // If the trip time is less than the minimum trip time found so far and the driver can still get back to the depot
      // set the minTripTime to this trip and set result equal to this load.
      if (tripTime < minTripTime && isValidTrip(load, tripTime)) {
        minTripTime = tripTime;
        result = load;
      }
    }

    // If the code found a load that works for the driver add the load to the driver's loadList.
    if (result != null) {
      addLoad(result, minTripTime);
    }

    return result;
  }

  /**
   * Prints the driver's loadList.
   */
  public void print() {
    System.out.println(loadList);
  }

  // Checks if the driver can still get back to the depot if it delivers the load.
  private boolean isValidTrip(Load load, double tripTime) {
    // Add the driver's total time to the load's trip time and finally to the time it would take the driver to drive from the load's end location
    // to the depot's location.
    return time + tripTime + load.getEndLoc().calculateDistance(DEPOT_LOCATION) <= MAX_DRIVE_MINUTES;
  }


  private void addLoad(Load load, double tripTime) {
    // Add the tripTime to the driver's total time.
    time += tripTime;
    // Add the load to the driver's loadList.
    loadList.add(load.getId());
    // Set the driver's current location to the end location of the load.
    curLocation = load.getEndLoc();
  }
}
