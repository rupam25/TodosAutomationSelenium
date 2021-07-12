package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

import static Utils.UtilConstants.*;

public class TestRunData {
    static Properties properties = new Properties();
    Logger log = LoggerFactory.getLogger(this.getClass());

    public TestRunData() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(getPropertyFilePath()));

            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("project.properties not found at " + getPropertyFilePath());

        }
    }

    public String getChromeDriverPath() {
        String driverPath = properties.getProperty(CHROME_DRIVER_PROPERTY);
        return driverPath;

    }

    public String getFirefoxDriverPath() {
        String driverPath = properties.getProperty(FIREFOX_DRIVER_PROPERTY);
        return driverPath;
    }

    public String getUiBaseUrl() {
        String url = properties.getProperty(BASE_URL_PROPERTY);
        log.debug(url);
        return url;
    }

    public String getRunBrowser() {
        return System.getProperty(BROWSER_NAME_PROPERTY);
    }

    public String getReportsFolder() {
        return properties.getProperty(REPORT_FOLDER_PROPERTY);
    }

    public Integer getShortWait() {
        return Integer.valueOf(properties.getProperty(SHORT_TIMEOUT_SECONDS_PROPERTY));
    }

    public String getPropertyFilePath(){
        return System.getProperty(PROPERTIES_FILEPATH_PROPERTY);
    }
}
