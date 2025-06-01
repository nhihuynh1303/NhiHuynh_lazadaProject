import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends BasePage{
    WebDriver driver;
    public SearchPageObject(WebDriver driver){
        this.driver = driver;
    }

    public String getSearchResultHeaderText() {
        return getTextElement(driver, SearchPageUI.SEARCH_RESULT_HEADER_TEXT);
    }

    public String getSearchResultItemsFoundText() {
        return getTextElement(driver, SearchPageUI.SEARCH_RESULT_ITEMS_FOUND_TEXT);
    }

    public void inputPriceMinField(String minPrice) {
        sendKeyToElement(driver, SearchPageUI.PRICE_MIN_FIELD, minPrice);
    }

    public void inputPriceMaxField(String maxPrice) {
        sendKeyToElement(driver, SearchPageUI.PRICE_MAX_FIELD, maxPrice);
    }

    public void clickOnSearchPriceButton() {
        waitForElementClickable(driver, SearchPageUI.PRICE_SEARCH_BUTTON);
        clickToElement(driver, SearchPageUI.PRICE_SEARCH_BUTTON);
    }

    protected String getPriceFilterText(){
        return getTextElement(driver, SearchPageUI.PRICE_FILTER_TEXT).trim();
    }

    public void clickToSortBySelectBox() {
        waitForElementClickable(driver, SearchPageUI.SORT_BY_SELECT_BOX);
        clickToElement(driver, SearchPageUI.SORT_BY_SELECT_BOX);
        sleepInSecond(3);
    }

    public void selectLowPriceToHight(String LowPriceToHighValue) {
        selectItemInCustomDropdown(driver, SearchPageUI.SORT_BY_SELECT_BOX_VALUE, LowPriceToHighValue );
    }

    public void scrollToSortBySelecbox() {
        scrollOnTopByJS(driver);
    }

    public String getSortByText() {
        return getTextElement(driver, SearchPageUI.SORT_BY_SELECT_BOX);
    }

    public void switchToPage(String pageNumber) {
        waitForElementClickable_Dynamic(driver, SearchPageUI.DYNAMIC_NAVIGATION_PAGE_NUMBER, pageNumber);
        clickToElement_Dynamic(driver, SearchPageUI.DYNAMIC_NAVIGATION_PAGE_NUMBER, pageNumber);
        sleepInSecond(3);
    }

    public void scrollToNavigationPageBar() {
        scrollOnBottomElementByJS(driver, SearchPageUI.NAVIGATION_PAGE_BAR);
    }

    public void listAllItemProductName() {
        waitForElementPresence(driver, SearchPageUI.ALL_ITEM_PRODUCT_NAME );
        List<WebElement> allItems = getListElements(driver, SearchPageUI.ALL_ITEM_PRODUCT_NAME);
        for (WebElement item : allItems){
            System.out.println("Item Name: " + item.getText());
        }
    }

    public boolean isBreadcrumbDisplay() {
        return isElementDisplayed(driver, SearchPageUI.BREADCRUMB_TEXT);
    }

    public void saveItemNameToArray(String filePath, List<String> itemNames ) {
        List<WebElement> allItems = getListElements(driver, SearchPageUI.ALL_ITEM_PRODUCT_NAME);
        for (WebElement item : allItems) {
            String itemName = item.getText();
            itemNames.add(itemName);
        }
    }

    public void exportItemNameToFile(String filePath, List<String> itemNames) {
        writeItemNamesToExcel(itemNames, filePath);
    }
}
