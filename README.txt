=========================
* SENG3400 Assignment 2 *
* Simon Hartcher        *
* C3185790              *
=========================

All source files (including WSDL) are found in the 'src' folder.

Server files are in the 'server' folder:
1. Copy MyLTCAdmin.java and MyLTCServer.java to the axis webapps directory
2. Rename them to .jws
3. Build WEB-INF/MyLTCState.java and copy to the axis 'WEB-INF/classes' directory
  i.e. javac -classpath "..." -d "/path/to/WEB-INF/classes" MyLTCState.java

Client files are in the 'client' folder:
1. Build MyUserAdmin.java and MyUserClient.java with the axis libs in the classpath
2. Run them without arguments to see usage instructions
  i.e. java MyUserAdmin
