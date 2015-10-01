import localhost.axis.MyLTCAdmin_jws.*;

public class MyUserAdmin {
  private static MyLTCAdminService service;
  private static MyLTCAdmin server;

  public static void main(String[] args) {
    try {
      service = new MyLTCAdminServiceLocator();
      server = service.getMyLTCAdmin();

      System.exit(0);
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }
}
