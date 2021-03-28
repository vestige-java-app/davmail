package davmail.launcher;

import java.io.File;

import davmail.BundleMessage;
import davmail.DavGateway;
import davmail.Settings;
import davmail.ui.tray.DavGatewayTray;

public class DavmailLauncher implements Runnable {

    private File base;

    public DavmailLauncher(final File base) {
        this.base = base;
    }

    public void run() {
        Settings.setConfigFilePath(new File(base, "davmail.properties").getAbsolutePath());

        Settings.load();
        Settings.setProperty("davmail.logFilePath", new File(base, "logs").getAbsolutePath());
        Settings.setProperty("davmail.server", "true");

        DavGatewayTray.init(true);

        DavGateway.start();

        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            DavGatewayTray.debug(new BundleMessage("LOG_GATEWAY_INTERRUPTED"));
            DavGateway.stop();
            DavGatewayTray.debug(new BundleMessage("LOG_GATEWAY_STOP"));
        }
    }

}
