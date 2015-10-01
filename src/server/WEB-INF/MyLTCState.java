/*
 * Simon Hartcher
 * C3185790
 * SENG3400
 */

import java.util.Map;
import java.util.HashMap;

/**
 * Static class for holding LTC state
 */
public class MyLTCState {
  private static final String userName = "admin";
  private static final String password = "converter";
  private static int callCount = 0;
  private static final Map<String, Double> locations;
  static {
    //init locations
    locations = new HashMap<String, Double>();
    locations.put("sydney", 10d);
    locations.put("amsterdam", 2d);
    locations.put("los angeles", -7d);
    locations.put("beijing", 8d);
  }

  /**
   * Validates the username and password
   */
  public static boolean validate(String user, String pwd) {
    return userName.equals(user) && pwd.equals(pwd);
  }

  /**
   * Increments the call count
   */
  public static void incrementCallCount() {
    callCount++;
  }

  /**
   * Retrieves the call count
   */
  public static int getCallCount() {
    return callCount;
  }

  /**
   * Retrieves the offset for the given location
   */
  public static Double getOffset(String location) {
    return locations.get(location.toLowerCase());
  }

  /**
   * Adds a new location with the given offset
   */
  public static boolean addLocation(String location, double offset) {
    //return if the location already exists
    if (locations.containsKey(location.toLowerCase()))
      return false;

    //add it
    locations.put(location.toLowerCase(), offset);
    return true;
  }

  /**
   * Remove the given location
   */
  public static void removeLocation(String location) {
    locations.remove(location.toLowerCase());
  }

  /**
   * Retrieve a string array of locations
   */
  public static String[] getLocations() {
    //create result array
    String[] keys = new String[locations.size()];

    //assign locations to results
    int index = 0;
    for (Object key: locations.keySet()) {
      String location = (String)key;
      keys[index++] = location.toUpperCase();
    }

    return keys;
  }

  /**
   * Determines whether the location exists
   */
  public static boolean hasLocation(String location) {
    return locations.containsKey(location.toLowerCase());
  }
}
