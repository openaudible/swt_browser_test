Simple MacOS Application to demonstrate crash in webkit when run via Java Eclipse SWT on Ventura on Intel x86-64.

Run by double clicking the application, or by running:
SWTBrowserTest.app/Contents/MacOS/JavaApplicationStub
Some arguments:
SWTBrowserTest.app/Contents/MacOS/JavaApplicationStub --help
will let you set the delay before exit (--delay N) or URL to load (--url page)


Also can be run from java, which usually does not crash:
./SWTBrowserTest.app/Contents/Resources/jre.bundle/Contents/Home/bin/java -XstartOnFirstThread -jar ./SWTBrowserTest.app/Contents/Resources/app/mac_x86_64-jar-with-dependencies.jar --delay 5

Expected behavior: Shows a brief page with a google map then quits with exit code 0.
Buggy behavior (Ventura/Intel): Shows a window, closes, and a Crash Reporter Dialog comes up.


Building:
mvn clean compile package
Running:
java -XstartOnFirstThread -jar target/mac_x86_64-jar-with-dependencies.jar

The -XstartOnFirstThread is required for Mac. For Linux and Windows, it is not needed.

Installer to package DMG requires Install4j 9.

