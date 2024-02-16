public class Location {

  private final double xCoor;
  private final double yCoor;

  public Location(double xCoor, double yCoor) {
    this.xCoor = xCoor;
    this.yCoor = yCoor;
  }

  /**
   * Calculates the distance this location is from the location passed in.
   *
   * @return the distance this location is from the location passed in.
   */
  public double calculateDistance(Location loc) {
    return Math.sqrt(Math.pow(this.xCoor - loc.xCoor, 2) + Math.pow(this.yCoor - loc.yCoor, 2));
  }

  /**
   * Builder class that builds a Location from a String representing the Location.
   */
  public static class Builder {
    // String in the format "(X,Y)"
    private String rawString;

    public Builder setRawString(String rawString) {
      this.rawString = rawString;
      return this;
    }

    public Location build() {

      // Strip off the leading '(' and end at the ','.
      double xCoor = Double.parseDouble(rawString.substring(1, rawString.indexOf(',')));
      // Start at the ',' and strip off the trailing ')'.
      double yCoor = Double.parseDouble(rawString.substring(rawString.indexOf(',') + 1, rawString.length() - 1));

      return new Location(xCoor, yCoor);

    }
  }
}
