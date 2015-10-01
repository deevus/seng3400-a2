public class MyLTCServer {
  /**
   * Retrieve the time offset (relative to GMT) for a specific location
   * @param  location The location from which to retrieve the time offset
   * @return          The time offset for the given location
   */
  public Double currentOffset(String location) {
    MyLTCState.incrementCallCount();

    return MyLTCState.getOffset(location);
  }

  /**
   * Retrieve a list of currently supported locations
   * @return Returns a String containing each of the currently supported
   * locations, separated by a newline.
   */
  public String listLocations() {
    MyLTCState.incrementCallCount();
    String[] locations = MyLTCState.getLocations();

    String result = "";
    for (String location: locations) {
      result += location + "\n";
    }

    return result;
  }

  /**
   * Convert a time in one location to the time in another
   * @param   from  The location to convert the time from
   * @param   to    The location to convert the time to
   * @param   time  The time in the "from" location
   * @return  The time in the location "to"
   */
  public String convert(String from, String to, String time) {
    MyLTCState.incrementCallCount();

    //convert time to minutes
    String[] timeSegments = time.split(":");
    int hours = Integer.parseInt(timeSegments[0]);
    int minutes = Integer.parseInt(timeSegments[1]);
    int totalMinutes = (hours * 60) + minutes;

    Double fromOffset = MyLTCState.getOffset(from);
    Double toOffset = MyLTCState.getOffset(to);
    Double delta = toOffset - fromOffset;
    Double deltaInMinutes = delta * 60;

    Double newTimeInMinutes = totalMinutes + deltaInMinutes;
    Double newMinutes = (newTimeInMinutes % 60 + 60) % 60;
    Double newHours = ((newTimeInMinutes - newMinutes) / 60 + 24) % 24;

    return String.format("%02.0f:%02.0f", newHours, newMinutes);
  }
}
