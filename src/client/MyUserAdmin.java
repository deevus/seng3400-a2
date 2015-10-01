import localhost.axis.MyLTCAdmin_jws.*;

public class MyUserAdmin {
  private static MyLTCAdminService service;
  private static MyLTCAdmin server;

  public static void main(String[] args) {
    try {
      service = new MyLTCAdminServiceLocator();
      server = service.getMyLTCAdmin();

      if (args.length < 1) {
        printUsage();
        System.exit(0);
      }

      processCommand(args);

      System.exit(0);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static void processCommand(String[] arguments) {
    try {
      String user = arguments[0];
      String password = arguments[1];
      String command = arguments[2];
      String location; double offset; boolean result;

      switch (command.toLowerCase()) {
        case "addlocation":
          location = arguments[3];
          offset = Double.parseDouble(arguments[4]);
          result = server.addLocation(user, password, location, offset);
          System.out.println(result ? "New location added" : "Location already exists or user unauthorised");
          break;
        case "setoffset":
          location = arguments[3];
          offset = Double.parseDouble(arguments[4]);
          result = server.setOffset(user, password, location, offset);
          System.out.println(result ? "Location updated successfully" : "Location does not exist or user unauthorised");
          break;
        case "callcount":
          int callCount = server.callCount(user, password);
          System.out.println(callCount >= 0 ? "Call Count: " + callCount : "User unauthorised");
          break;
        default:
          System.err.printf("Invalid command '%s'\n", command);
          break;
      }
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Error: Invalid number of arguments");
    }
    catch (Exception e) {
      System.err.println("Error: Invalid arguments");
      e.printStackTrace();
    }
  }

  private static void printUsage() {
    System.out.println("Usage: java MyUserAdmin <user> <password> <command> <arguments>");
    System.out.println("Available commands:");
    printUsage("addLocation <location> <offset>", "Add a new location to the system with its current time offset relative to GMT");
    printUsage("setOffset <location> <offset>", "Update the time offset for a specific location");
    printUsage("callCount", "Retrieve the number of client web service calls");
  }

  private static void printUsage(String command, String description) {
    int rightPadding = 35;
    System.out.printf("    %1$-" + rightPadding + "s %2$s\n", command, description);
  }

}
