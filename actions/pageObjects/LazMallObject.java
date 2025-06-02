import org.openqa.selenium.WebDriver;

public class LazMallObject extends BasePage {
    WebDriver driver;
    public LazMallObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean verifyTheExampleText() {
        return isElementDisplayed(driver, LazMallUI.VERIFYTEXTEXAMPLE);
    }
}
