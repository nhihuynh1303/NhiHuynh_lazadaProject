import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static HomePageObject getHomePage (WebDriver driver){
        return new HomePageObject(driver);
    }
    public static SearchPageObject getSearchPage (WebDriver driver){
        return new SearchPageObject(driver);
    }
    public static LazMallObject getLazMall (WebDriver driver){
        return new LazMallObject(driver);
    }

}
