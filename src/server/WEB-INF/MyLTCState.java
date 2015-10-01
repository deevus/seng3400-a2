import java.util.Map;
import java.util.HashMap;

public class MyLTCState {
  private static final String userName = "admin";
  private static final String password = "converter";
  private static int callCount = 0;
  private static final Map<String, Double> locations;
  static {
    locations = new HashMap<String, Double>();
    locations.put("sydney", 10d);
    locations.put("amsterdam", 2d);
    locations.put("los angeles", -7d);
    locations.put("beijing", 8d);
  }

  public static boolean validate(String user, String pwd) {
    return userName.equals(user) && pwd.equals(pwd);
  }

  public static void incrementCallCount() {
    callCount++;
  }

  public static int getCallCount() {
    return callCount;
  }

  public static Double getOffset(String location) {
    return locations.get(location.toLowerCase());
  }

  public static boolean addLocation(String location, double offset) {
    if (locations.containsKey(location.toLowerCase()))
      return false;

    locations.put(location.toLowerCase(), offset);
    return true;
  }

  public static void removeLocation(String location) {
    locations.remove(location.toLowerCase());
  }

  public static String[] getLocations() {
    String[] keys = new String[locations.size()];
    int index = 0;
    for (Object key: locations.keySet()) {
      String location = (String)key;
      keys[index++] = location.toUpperCase();
    }

    return keys;
  }

  public static boolean hasLocation(String location) {
    return locations.containsKey(location.toLowerCase());
  }
}
