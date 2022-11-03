package org.example.swt_browser_sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

// Opens a URL in an SWT browser then closes it after a short delay upon success.
public class SWTBrowserTest {
    private static final Log log = LogFactory.getLog(SWTBrowserTest.class);
    static int delay = 1000;

    public static String help() {
        String out = "";
        out = "SWTBrowser Test App. Opens an SWT browser url, waits for a delay length of " + delay + "ms, and exits.\n";
        out += "Optional arguments:\n";
        out += "--delay (number of milliseconds, or -1 too not exit automatically.\n";
        out += "--url (page to load)\n";
        out += "--help  Displays this message\n";
        return out;
    }

    public static void main(String[] args) {
        try {
            String url = "https://google.com/maps";
            // Parse arguments
            for (int x = 0; x < args.length; x++) {
                String cmd = args[x];
                switch (cmd) {
                    case "--delay":
                        delay = Integer.parseInt(args[x + 1]);
                        break;
                    case "--url":
                        url = args[x + 1];
                        break;
                    case "--help":
                        System.out.println(help());
                        System.exit(0);
                        break;
                }
            }
            System.out.println("Starting SWT Test app. URL=" + url + " exit delay=" + delay);
            Display display = new Display();        // Display is like a window manager.
            final Shell shell = new Shell(display); // shell is a window
            shell.setText("SWT Browser Example");
            shell.setSize(800, 800);
            SWTBrowserExample e = new SWTBrowserExample(shell, url);

            shell.open();
            e.browser.addLocationListener(new LocationAdapter() {
                @Override
                public void changed(LocationEvent event) {
                    super.changed(event);
                    log.info("Successfully loaded page.");
                    if (delay >= 0) {
                        log.info("Quitting in " + delay + "ms");
                        display.timerExec(delay, () -> shell.dispose());
                    }
                }
            });

            // GUI loop to handle events until done.
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }
            display.dispose();
            log.info("Test success.");
            System.exit(0);
        } catch (Throwable th) {
            log.error("Test fail", th); // this shouldn't happen. Ventura bug will not get here.
            System.exit(-1);
        }
    }

}
