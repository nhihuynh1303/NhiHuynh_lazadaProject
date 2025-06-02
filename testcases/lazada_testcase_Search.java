import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class lazada_testcase_Search extends BaseTest{
    private WebDriver driver;
    private HomePageObject homePage;
    private SearchPageObject searchPage;
    private LazMallObject lazMallPage;

    private String keyWord = "Logitech Keyboard";
    private String minPrice = "150000";
    private String maxPrice = "4000000";
    private String LowPriceToHighValue = "Price low to high";

    private List<String> itemNames = new ArrayList<>();

    private String projectPath = System.getProperty("user.dir");
    private String fileName = "item_names.xlsx";
    private String filePath = projectPath+ File.separator+"exportFiles"+File.separator+fileName;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        homePage = PageGeneratorManager.getHomePage(driver);
    }
    @Test
    public void TC_01_inputSearchKeyWord() {
        // enter Keyword
        homePage.clickOnSearchField();
        homePage.enterKeywordOnSearchField(keyWord);

        searchPage = homePage.clickOnSearchButton(); // navigate to search page after clicking on Search button

        // Verify search result text header
        Assert.assertEquals(searchPage.getSearchResultHeaderText(), keyWord);
        Assert.assertEquals(searchPage.getSearchResultItemsFoundText(), keyWord);

    }

    @Test
    public void TC_02_inputPrice(){
        // input price min to max
        searchPage.inputPriceMinField(minPrice);
        searchPage.inputPriceMaxField(maxPrice);
        searchPage.clickOnSearchPriceButton();

        // verify Filter price text
        Assert.assertEquals(searchPage.getPriceFilterText(), "Price: "+minPrice+"-"+maxPrice);
        // verify breadcrumb
        Assert.assertTrue(searchPage.isBreadcrumbDisplay());

        // Verify search result text header
        Assert.assertEquals(searchPage.getSearchResultHeaderText(), keyWord);
        Assert.assertEquals(searchPage.getSearchResultItemsFoundText(), keyWord);
    }

    @Test
    public void TC_03_selectLowPriceToHigh(){
        // select value from selectbox
        searchPage.scrollToSortBySelecbox();
        searchPage.clickToSortBySelectBox();
        searchPage.selectLowPriceToHight(LowPriceToHighValue);

        // verify text on selecbox after new value is selected
        Assert.assertEquals(searchPage.getSortByText(), LowPriceToHighValue);

        // verify breadcrumb
        Assert.assertTrue(searchPage.isBreadcrumbDisplay());

        // Verify search result text header
        Assert.assertEquals(searchPage.getSearchResultHeaderText(), keyWord);
        Assert.assertEquals(searchPage.getSearchResultItemsFoundText(), keyWord);
    }

    @Test
    public void TC_04_getItemName(){
        //searchPage.listAllItemProductName(); -> use it to print Item Names page 1 on console

        searchPage.saveItemNameToArray(filePath, itemNames); // save Item Names page 1 to Array

        // swtich to Page 2 to get item names
        searchPage.scrollToNavigationPageBar();
        searchPage.switchToPage("2");

        //searchPage.listAllItemProductName(); -> use it to print Item Names page 2 on console
        searchPage.saveItemNameToArray(filePath, itemNames); // save Item Names page 2 to Array

        // swtich to Page 3 to get item names
        searchPage.scrollToNavigationPageBar();
        searchPage.switchToPage("3");

        //searchPage.listAllItemProductName(); -> use it to print Item Names page 3 on console

        searchPage.saveItemNameToArray(filePath, itemNames); // save Item Names page 3 to Array

        // System.out.println("Array name file size: "+itemNames.size());  -> use it to check Array size

        searchPage.exportItemNameToFile(filePath, itemNames);
    }

    // use to demo when creating new page object on the video recording, exp: LazMall page
    @Test
    public void TC_05_navigateLazMall(){
        lazMallPage = searchPage.naviagteLazMall();
        Assert.assertTrue(lazMallPage.verifyTheExampleText());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
