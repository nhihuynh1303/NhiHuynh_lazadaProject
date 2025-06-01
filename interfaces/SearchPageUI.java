public class SearchPageUI {
    public static final String SEARCH_RESULT_HEADER_TEXT = "xpath=//h1[@class='JrAyI']";
    public static final String SEARCH_RESULT_ITEMS_FOUND_TEXT ="xpath=//span[contains(text(),'items found for')]/following-sibling::span[1]";
    public static final String PRICE_MIN_FIELD ="xpath=//input[@placeholder='Min']";

    public static final String PRICE_MAX_FIELD ="xpath=//input[@placeholder='Max']";
    public static final String PRICE_SEARCH_BUTTON ="xpath=//input[@placeholder='Max']//following-sibling::button";

    public static final String PRICE_FILTER_TEXT = "xpath=//span[contains(text(),'Filtered By')]/following-sibling::span[contains(.,'Price')]";

    public static final String SORT_BY_SELECT_BOX = "xpath=//span[@class='ant-select-selection-item']/div";

    public static final String SORT_BY_SELECT_BOX_VALUE = "xpath=//div[contains(@class,'ant-select-item')]//div[@data-spm-click]";

    public static final String BREADCRUMB_TEXT ="XPATH=//span[@class='breadcrumb_item_text']/span[text()='Search Results']";

    public static final String NAVIGATION_PAGE_BAR = "XPATH=//ul[contains(@class,'ant-pagination')]";

    public static final String ALL_ITEM_PRODUCT_NAME ="XPATH=//div[@data-qa-locator='product-item']//div[@class='RfADt']/a";

    public static final String DYNAMIC_NAVIGATION_PAGE_NUMBER = "XPATH=//ul[contains(@class,'ant-pagination')]//li[@title='%s']";


}
