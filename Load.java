public class Load {

  // ID of the load.
  private final int id;
  // Start location of the load.
  private final Location startLoc;
  // End location of the load.
  private final Location endLoc;
  // Distance from start location to end location.
  private final double distance;

  private Load(int id, Location startLoc, Location endLoc) {
    this.id = id;
    this.startLoc = startLoc;
    this.endLoc = endLoc;
    this.distance = startLoc.calculateDistance(endLoc);
  }

  /**
   * Trip time is the distance from the location passed in to the starting location of the load plus
   * the total distance of the load.
   * @param location is the current location of the driver.
   * @return Trip time.
   */
  public double calculateTripTime(Location location) {
    return startLoc.calculateDistance(location) + distance;
  }

  /**
   * @return ID of the load.
   */
  public int getId() {
    return id;
  }

  /**
   * @return start location of the load.
   */
  public Location getStartLoc() {
    return startLoc;
  }

  /**
   * @return end location of the load.
   */
  public Location getEndLoc() {
    return endLoc;
  }

  /**
   * @return distance from start location to end location.
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Builder class the builds a Load from a String representing the Load.
   */
  public static class Builder {

    // String in the format "ID (X1,Y1) (X2,Y2)"
    private String rawString;

    public Builder setRawString(String rawString) {
      this.rawString = rawString;
      return this;
    }

    public Load build() {

      String[] parts = rawString.split(" ");

      return new Load(Integer.parseInt(parts[0]),
                      new Location.Builder().setRawString(parts[1]).build(),
                      new Location.Builder().setRawString(parts[2]).build());

    }

  }
}
