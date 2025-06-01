import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage{
    WebDriver driver;
    public HomePageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnSearchField() {
        waitForElementClickable(driver, HomePageUI.SEARCH_FIELD);
        clickToElement(driver, HomePageUI.SEARCH_FIELD);

    }

    public void enterKeywordOnSearchField(String keyWord) {
        sendKeyToElement(driver, HomePageUI.SEARCH_FIELD, keyWord );
    }

    public SearchPageObject clickOnSearchButton() {
        waitForElementClickable(driver, HomePageUI.SEARCH_BUTTON);
        clickToElement(driver, HomePageUI.SEARCH_BUTTON);
        return PageGeneratorManager.getSearchPage(driver);
    }
}
