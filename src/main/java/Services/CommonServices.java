package Services;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import Utils.TestRunData;

public class CommonServices {
    public WebElement element;
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;
    public static TestRunData data = new TestRunData();


    public CommonServices(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method will fill data in input text
     * @param string Input String
     * @param element Input Element
     * @param isClearRequired
     */
    public void fillData(String string, WebElement element, Boolean isClearRequired) {
        if (isClearRequired) {
            element.clear();
        }
        element.sendKeys(string);
        element.sendKeys(Keys.ENTER);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public Boolean isDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public Boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public String getAttribute(WebElement element, String attributeName) {
        return element.getAttribute(attributeName);
    }

}
