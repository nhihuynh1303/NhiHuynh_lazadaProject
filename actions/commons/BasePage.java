import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BasePage {
    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toUpperCase().startsWith("ID")) {
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.toUpperCase().startsWith("CLASS")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.toUpperCase().startsWith("NAME")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.toUpperCase().startsWith("TAGNAME")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.toUpperCase().startsWith("CSS")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.toUpperCase().startsWith("XPATH")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!!!!");
        }

        return by;
    }
    public WebElement getElement(WebDriver driver, String locator){
        return driver.findElement(getByLocator(locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver, locator).isDisplayed();
    }

    public String getTextElement(WebDriver driver, String locator){
        return getElement(driver, locator).getText();
    }

    public void clickToElement(WebDriver driver, String locator){
        getElement(driver, locator).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keysToSend){
        getElement(driver, locator).clear();
        getElement(driver, locator).sendKeys(keysToSend);
    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<WebElement> getListElements(WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }

    public void waitForElementPresence(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }
    public void scrollOnTopByJS(WebDriver driver) {
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    public void scrollOnBottomElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void selectItemInCustomDropdown(WebDriver driver, String selectBoxValueLocator, String expectedItem) {
        List<WebElement> allItems = getListElements(driver, selectBoxValueLocator);
        waitForElementPresence(driver, selectBoxValueLocator);
        for (WebElement item : allItems) {
            if (item.getText().equals(expectedItem)) {
                item.click();
                sleepInSecond(3);
                break;
            }
        }
    }


    // APPLY FOR DYNAMIC LOCATOR
    public String castRestParameter(String locator, String... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }
    public void clickToElement_Dynamic(WebDriver driver, String locator, String... restParameter){
        getElement(driver,castRestParameter(locator, restParameter)).click();
    }
    public void waitForElementClickable_Dynamic(WebDriver driver, String locator, String... restParameter){
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, restParameter))));
    }

    //====
    public void writeItemNamesToExcel(List<String> itemNames, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Item Names");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Item Names have total: " +itemNames.size() );

        int rowNum = 1;
        for (String itemName : itemNames) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(itemName);
        }

        sheet.autoSizeColumn(0);

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Item names written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
