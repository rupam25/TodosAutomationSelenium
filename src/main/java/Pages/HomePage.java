package Pages;

import Services.CommonServices;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.util.List;

public class HomePage extends CommonServices {
    /* ---------- Default Page elements------------ */
    @FindBy(css = "header[class=\"header\"] h1")
    public WebElement pageHeader;
    @FindBy(css = "header[class=header] input")
    public WebElement searchInput;
    @FindBy(css = "footer[class=\"info\"] p")
    public List<WebElement> footerElements;

    /* ---- Elements available after search operation----- */
    @FindBy(css = "section[class=\"main\"]")
    public WebElement searchResultSection;

    @FindBy(css = "div[class=view] button")
    public List<WebElement> closeButton;

    @FindBy(css = "div[class=view] input")
    public List<WebElement> radioButton;

    @FindBy(css = "div[class=view] label")
    public List<WebElement> resultData;

    @FindBy(css = "footer[class=\"footer\"]")
    public WebElement totalSearchData;

    /*---- Element available after Radio Button selection---- */
    @FindBy(css = "button[class=\"clear-completed\"]")
    public WebElement clearBtn;

    /* --- Element present after double click-----*/
    @FindBy(css = "input[class=\"edit\"]")
    public WebElement searchEdit;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, data.getShortWait()), this);
    }

    public String getPageHeader() { return pageHeader.getText(); }


    public String getFooterText(int index) {
        return footerElements.get(index).getText();
    }


}
