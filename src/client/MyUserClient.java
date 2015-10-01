/*
 * Simon Hartcher
 * C3185790
 * SENG3400
 */

import localhost.axis.MyLTCServer_jws.*;

public class MyUserClient {
  private static MyLTCServerService service;
  private static MyLTCServer server;

  /**
   * MyUserClient entry point
   */
  public static void main(String[] args) {
    try {
      //load service
      service = new MyLTCServerServiceLocator();
      server = service.getMyLTCServer();

      //if we don't have any arguments
      if (args.length < 1) {
        printUsage();
        System.exit(0);
      }

      processCommand(args);

      System.exit(0);
    }
    catch (Exception e) {
      //some uncaught error
      e.printStackTrace();
      System.exit(1);
    }
  }

  private static void processCommand(String[] arguments) {
    try {
      //get command
      String command = arguments[0];

      //process given command
      switch (command.toLowerCase()) {
        case "listlocations":
          System.out.print(server.listLocations());
          break;
        case "currentoffset":
          String location = arguments[1];
          double offset = server.currentOffset(location);
          System.out.printf("%s: %.2f\n", location.toUpperCase(), offset);
          break;
        case "convert":
          String from = arguments[1];
          String to = arguments[2];
          String time = arguments[3];

          String toTime = server.convert(from, to, time);
          System.out.printf("%s: %s\n%s: %s\n", from.toUpperCase(), time, to.toUpperCase(), toTime);
          break;
        default:
          System.err.printf("Invalid command '%s'\n", command);
          break;
      }
    }
    catch (ArrayIndexOutOfBoundsException e) {
      //if too many arguments are supplied
      System.err.println("Error: Invalid number of arguments");
    }
    catch (Exception e) {
      //general error
      System.err.println("Error: Invalid arguments");
      e.printStackTrace();
    }
  }

  private static void printUsage() {
    System.out.println("Usage: java MyUserClient <command> <arguments>");
    System.out.println("Available commands:");
    printUsage("currentOffset <location>", "Retrieve the time offset for a specific location");
    printUsage("listLocations", "Retrieve a list of currently supported locations");
    printUsage("convert <from> <to> <time>", "Convert a time in one location to the time in another");
  }

  private static void printUsage(String command, String description) {
    int rightPadding = 30;
    System.out.printf("    %1$-" + rightPadding + "s %2$s\n", command, description);
  }
}
