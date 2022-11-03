package org.example.swt_browser_sample;


import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.LocationAdapter;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.Browser;

// Basic swt browser.

public class SWTBrowserExample {

    enum BTN {Back, Forward, Refresh, Stop, Go}
    final ToolBar toolbar;
    final Browser browser;
    final Text text;

    // Chromium chromium;
    public Browser newBrowser(Composite parent)
    {
        System.setProperty("swt.chromium.suspendThreads", "true");
        return new Browser(parent, SWT.NONE);
    }


    public SWTBrowserExample(Composite parent, String url) {
        parent.setLayout(new GridLayout(1, false));
        toolbar = new ToolBar(parent, SWT.NONE);
        toolbar.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));

        for (BTN b : BTN.values()) {
            ToolItem item = new ToolItem(toolbar, SWT.PUSH);
            item.setText(b.name());
            item.setData(b);
            item.addListener(SWT.Selection, event -> {
                click((BTN) event.widget.getData());
            });
        }

        text = new Text(parent, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
        browser = newBrowser(parent);
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text.addListener(SWT.DefaultSelection, e -> browser.setUrl(text.getText()));

        browser.setUrl(url);
        browser.addLocationListener(new LocationAdapter() {
            @Override
            public void changing(LocationEvent event) {
                text.setText(event.location);
            }
            @Override
            public void changed(LocationEvent event) {
                text.setText(event.location);
            }
        });

    }

    void click(BTN b) {
        switch (b) {
            case Back:
                browser.back();
                break;
            case Forward:
                browser.forward();
                break;
            case Refresh:
                browser.refresh();
                break;
            case Stop:
                browser.stop();
                break;
            case Go:
                browser:
                browser.setUrl(text.getText());
                break;
        }

    }

    public static void main(String[] args) {
        Display display = new Display();
        final Shell shell = new Shell(display);
        shell.setText("Browser Example");
        shell.setSize(800, 800);
        SWTBrowserExample e = new SWTBrowserExample(shell, "https://www.google.com/maps");
        shell.open();

        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}
