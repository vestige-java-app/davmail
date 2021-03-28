package davmail.installer;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;

public class DavmailInstaller {

    private File base;

    public DavmailInstaller(final File base) {
        this.base = base;
    }

    public void install() throws Exception {
        File file = new File(base, "davmail.properties");
        Properties properties = new Properties();
        InputStream inputStream = DavmailInstaller.class.getResourceAsStream("davmail.properties");
        try {
            properties.load(inputStream);
            FileWriter writer = new FileWriter(file);
            try {
                properties.store(writer, null);
            } finally {
                writer.close();
            }
        } finally {
            inputStream.close();
        }
    }

}
