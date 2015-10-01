public class MyLTCAdmin {
  /**
   * Add a new location to the system with its current time offset relative to GMT
   */
  public boolean addLocation(String user, String pwd, String location, double offset) {
    MyLTCState.incrementCallCount();

    if (!MyLTCState.validate(user, pwd)) return false;

    return MyLTCState.addLocation(location, offset);
  }

  /**
   * Update the time offset for a specific location (for example when Daylight
   * Saving Time commences or ceases for the location)
   */
  public boolean setOffset(String user, String pwd, String location, double offset) {
    MyLTCState.incrementCallCount();

    if (!MyLTCState.validate(user, pwd) | !MyLTCState.hasLocation(location)) {
      return false;
    }

    MyLTCState.removeLocation(location);
    return MyLTCState.addLocation(location, offset);
  }

  /**
   * Retrieve the number of client web service calls performed on either
   * interface since the server started
   */
  public int callCount(String user, String pwd) {
    MyLTCState.incrementCallCount();

    if (!MyLTCState.validate(user, pwd)) return -1;

    return MyLTCState.getCallCount();
  }
}
