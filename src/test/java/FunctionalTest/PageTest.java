package FunctionalTest;

import Services.BaseServices;
import Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import static java.lang.Boolean.*;
import static org.apache.commons.lang3.RandomStringUtils.*;
import static Utils.UtilConstants.*;


public class PageTest extends BaseServices {
    WebDriver driver;
    final Logger log = LoggerFactory.getLogger(PageTest.class);


    @BeforeMethod(alwaysRun = true)
    protected void beforeMethod()  {
        log.info("Launching browser");
        driver = getDriver(data.getRunBrowser());
        driver.get(data.getUiBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod() throws Exception {
        log.info("closing browser");
        driver.quit();
    }

    /**
     * TC-1
     * Open pageUrl and validate all default elements present on page.
     */
    @Test(description = "TC-1", groups = {"positiveCase"})
    public void navigateToPage() {
        SoftAssert soft = new SoftAssert();

        HomePage hPage = new HomePage(driver);
        log.info("Validate default status of all page elements");
        soft.assertEquals(hPage.getPageHeader(), PAGE_HEADER, "Correct Page header is displayed");
        soft.assertTrue(hPage.isDisplayed(hPage.searchInput) && hPage.isEnabled(hPage.searchInput), "Input box appears and is enabled");

        soft.assertEquals(hPage.getAttribute(hPage.searchInput, "placeholder"), INPUT_FIELD_LABEL, "Correct field label is shown");
        soft.assertEquals(hPage.getFooterText(0), FIRST_FOOTER_TEXT, "Correct text is shown in first line of footer");
        soft.assertEquals(hPage.getFooterText(1), SECOND_FOOTER_TEXT, "Correct text is shown for second line of footer");
        soft.assertEquals(hPage.getFooterText(2), THIRD_FOOTER_TEXT, "Correct text is shown for third line of footer");
        soft.assertAll();
    }

    /**
     * TC-2
     * Perform search with single data -> validate elements after search
     */
    @Test(description = "TC-2", groups = {"positiveCase"})
    public void validateSearch() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);

        String inputData = randomAlphabetic(5).toLowerCase();
        log.info("Perform search operation for data: " + inputData);
        hPage.fillData(inputData, hPage.searchInput, TRUE);

        log.info("Validate search result and all other new page elements");
        soft.assertTrue(hPage.isDisplayed(hPage.searchResultSection), "New section appears on search");
        soft.assertTrue(hPage.closeButton.size() == 1 && hPage.radioButton.size() == 1 && hPage.resultData.size() == 1,
                "Search with one data displays one radio button, one close icon and one text data");

        soft.assertEquals(hPage.getText(hPage.resultData.get(0)), inputData, "User input is shown in search result");
        soft.assertTrue(hPage.isDisplayed(hPage.totalSearchData), "New footer section appears on search");
        soft.assertEquals(hPage.getText(hPage.totalSearchData), FOOTER_DATA_ON_FIRST_SEARCH,
                "Footer section has correct text");
        soft.assertEquals(hPage.searchInput.getText(), "", "Search input box is blank");

        soft.assertAll();
    }

    /**
     * TC-3
     * Perform search with multiple data -> validate elements after search
     */
    @Test(description = "TC-3", groups = {"positiveCase"})
    public void checkMultipleSearch() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);
        String inputData1 = randomAlphabetic(5).toLowerCase();
        String inputData2 = randomAlphabetic(5).toLowerCase();

        log.info("Search twice with two different data");
        hPage.fillData(inputData1, hPage.searchInput, TRUE);
        hPage.fillData(inputData2, hPage.searchInput, TRUE);

        log.info("Validate all page elements");
        soft.assertTrue(hPage.isDisplayed(hPage.searchResultSection), "New section appears on search");
        soft.assertTrue(hPage.closeButton.size() == 2 && hPage.radioButton.size() == 2 && hPage.resultData.size() == 2,
                "Search with one data displays one radio button, one close icon and one text data");
        soft.assertEquals(hPage.getText(hPage.resultData.get(0)), inputData1, "First search data appears in result");
        soft.assertEquals(hPage.getText(hPage.resultData.get(1)), inputData2, "Second Search data appears in result");

        soft.assertTrue(hPage.isDisplayed(hPage.totalSearchData), "New footer section appears on search");
        soft.assertEquals(hPage.getText(hPage.totalSearchData), FOOTER_DATA_ON_SECOND_SEARCH,
                "Footer section has correct text");
        soft.assertEquals(hPage.searchInput.getText(), "", "Search input box is blank");

        soft.assertAll();

    }

    /**
     * TC-4
     * Perform search and select  radio button-> validate elements after button selection
     */
    @Test(description = "TC-4", groups = {"negativeCase"})
    public void verifyRadioBtnSelection() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);

        String inputData = randomAlphabetic(5).toLowerCase();
        log.info("Perform search with input data :" + inputData);
        hPage.fillData(inputData, hPage.searchInput, TRUE);

        hPage.radioButton.get(0).click();

        log.info("Validate all page elements on selection of radio button in search result");
        soft.assertTrue(hPage.radioButton.get(0).isSelected(), "Radio button is selected");
        soft.assertEquals(hPage.resultData.get(0).findElement(By.xpath("..//..")).getAttribute("class"), "completed");
        soft.assertTrue(hPage.totalSearchData.getText().contains(FOOTER_DATA_ON_ALLRADIO_BTN_SELECTION), "Compare search count is shown");
        soft.assertTrue(hPage.isDisplayed(hPage.clearBtn), "Clear completed button appears");
        soft.assertAll();
    }

    /**
     * TC-5
     * Perform search and uncheck selected radio button-> validate elements after button de-selection
     */
    @Test(description = "TC-5", groups = {"positiveCase"})
    public void verifyUncheckRadioBtn() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);
        String inputData = randomAlphabetic(5).toLowerCase();
        log.info("Perform search with data:" + inputData);
        hPage.fillData(inputData, hPage.searchInput, TRUE);

        Actions actions = new Actions(driver);
        actions.doubleClick(hPage.radioButton.get(0)).perform();

        log.info("verify all element status on double click operation on radio button of search result");
        soft.assertFalse(hPage.resultData.get(0).findElement(By.xpath("..//..")).getAttribute("class").contains("completed"), "Text is not strikeThrough");
        soft.assertFalse(hPage.radioButton.get(0).isSelected(), "button is not selected");
        soft.assertAll();
    }

    /**
     * TC-6
     * Perform search then Edit search data-> validate elements after Edit event
     */
    @Test(description = "TC-6", groups = {"negativeCase"})
    public void editSearchData() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);

        String inputData1 = randomAlphabetic(5).toLowerCase();
        log.info("Perform search with data :" + inputData1);
        hPage.fillData(inputData1, hPage.searchInput, Boolean.FALSE);

        Actions actions = new Actions(driver);
        actions.doubleClick(hPage.resultData.get(0)).perform();
        soft.assertEquals(hPage.resultData.get(0).findElement(By.xpath("..//..")).getAttribute("class"), "editing");

        log.info("edit search text after double click event");
        String inputData2 = randomAlphabetic(5).toLowerCase();
        hPage.fillData(inputData2, hPage.searchEdit, FALSE);

        log.info("Validate footer text after text update");
        soft.assertEquals(hPage.getText(hPage.totalSearchData), FOOTER_DATA_ON_FIRST_SEARCH,
                "Footer section has correct text");
        String modifiedInputData = inputData1.concat(inputData2);
        soft.assertEquals(hPage.resultData.get(0).getText(),modifiedInputData,"Concatenated string is shown in search result");
        soft.assertAll();
    }

    /**
     * TC-7
     * Perform search then Remove search data-> validate elements after removal
     */
    @Test(description = "TC-7", groups = {"negativeCase"})
    public void removeSearchData() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);
        String inputData = randomAlphabetic(5).toLowerCase();
        log.info("Perform search with data :" + inputData);
        hPage.fillData(inputData, hPage.searchInput, FALSE);

        Actions actions = new Actions(driver);
        actions.doubleClick(hPage.resultData.get(0)).perform();
        hPage.searchEdit.clear();

        log.info("Validate page element on removal of search text ");
        soft.assertEquals(hPage.getText(hPage.totalSearchData), FOOTER_DATA_ON_FIRST_SEARCH,
                "Footer section has correct text");
        soft.assertEquals(hPage.searchInput.getText(), "", "Search input box is blank");
        soft.assertTrue(hPage.closeButton.size() == 1 && hPage.radioButton.size() == 1,
                "Search with one data displays one radio button, one close icon ");
        soft.assertEquals(hPage.resultData.get(0).getText(), "", "Blank search result is shown");

        soft.assertAll();
    }

    /**
     * TC-8
     * Perform search and Select radio button for one data then perform clear operation-> validate elements after clear event
     */
    @Test(description = "TC-8", groups = {"negativeCase"})
    public void removeSelectedData() {

        SoftAssert soft = new SoftAssert();
        HomePage hPage = new HomePage(driver);

        log.info("Perform search operation with two different input data");
        String inputData1 = randomAlphabetic(5).toLowerCase();
        hPage.fillData(inputData1, hPage.searchInput, Boolean.FALSE);
        String inputData2 = randomAlphabetic(5).toLowerCase();
        hPage.fillData(inputData2, hPage.searchInput, Boolean.FALSE);

        soft.assertEquals(hPage.totalSearchData.getText(), FOOTER_DATA_ON_SECOND_SEARCH, "Correct item count is shown");

        log.info("Select radio button for first data");
        hPage.radioButton.get(0).click();

        soft.assertTrue(hPage.totalSearchData.getText().contains(FOOTER_DATA_ON_FIRST_SEARCH), "Correct item count is shown");
        soft.assertTrue(hPage.clearBtn.isDisplayed(), "Clear button appears on radio button selection");

        log.info("Remove first record by clicking clear button");
        hPage.clearBtn.click();

        soft.assertTrue(hPage.closeButton.size() == 1 && hPage.radioButton.size() == 1 && hPage.resultData.size() == 1,
                "Search with one data displays one radio button, one close icon and one text data");
        soft.assertTrue(hPage.resultData.get(0).getText().equals(inputData2), "Only second record appears");
        soft.assertAll();
    }

}
