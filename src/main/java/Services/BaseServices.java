package Services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utils.TestRunData;
import java.util.concurrent.TimeUnit;

import static Utils.UtilConstants.*;

public class BaseServices {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    public static WebDriver driver;
    public static TestRunData data = new TestRunData();

    /**
     * This method will verify firefox driver
     * @return firefox driver
     */
    public WebDriver getFirefoxDriver() {
        driver = new FirefoxDriver();
        System.setProperty(FIREFOX_DRIVER_PROPERTY, data.getFirefoxDriverPath());
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * This test method will return chrome driver
     * @return chrome driver
     */
    public WebDriver getChromeDriver() {
        driver = new ChromeDriver();
        System.setProperty(CHROME_DRIVER_PROPERTY, data.getChromeDriverPath());
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * This test method will return driver for specific browser
     * @param browser browserName
     * @return driver
     */
    public WebDriver getDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            return getChromeDriver();
        } else {
            return getFirefoxDriver();
        }
    }
}
