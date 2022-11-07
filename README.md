Simple MacOS Application to demonstrate crash in webkit when run via Java Eclipse SWT on Ventura on Intel x86-64.



Expected behavior: Shows a brief page with a google map then quits with exit code 0.
Buggy behavior (Ventura/Intel): Shows a window, closes, and a Crash Reporter Dialog comes up.

Download the DMG from https://github.com/openaudible/swt_browser_test/releases and open the DMG. 

Run by double clicking the app, or by running from Terminal:

```
cd /Volumes/SWTBrowserTest/
SWTBrowserTest.app/Contents/MacOS/JavaApplicationStub
```

Some optional arguments are available:

```
SWTBrowserTest.app/Contents/MacOS/JavaApplicationStub --help

SWTBrowser Test App. Opens an SWT browser url, waits for a delay length of 1000ms, and exits.
Optional arguments:
--delay (number of milliseconds, or -1 too not exit automatically.
--url (page to load)
--help  Displays this message
```

You can also run this using your own java or the one integrated into the .app:
```
./SWTBrowserTest.app/Contents/Resources/jre.bundle/Contents/Home/bin/java -XstartOnFirstThread -jar ./SWTBrowserTest.app/Contents/Resources/app/mac_x86_64-jar-with-dependencies.jar --delay 5
```


Building:
```
mvn clean compile package
```
Running:
```
java -XstartOnFirstThread -jar target/mac_x86_64-jar-with-dependencies.jar
```

The -XstartOnFirstThread is required for Mac. For Linux and Windows, it is not needed.

Installer to package DMG requires Install4j 9.

Any help finding the bug with Ventura WebKit is appreciated.
