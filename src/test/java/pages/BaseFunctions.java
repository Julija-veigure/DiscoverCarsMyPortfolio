package pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BaseFunctions {

    private final Logger LOGGER = (Logger) LogManager.getLogger(this.getClass());
    private WebDriver driver;
    private WebDriverWait wait;


    public BaseFunctions() {
        WebDriverManager.chromedriver().setup();
        LOGGER.info("Opening browser window");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {
        LOGGER.info("Opening " + url + " web page");
        driver.get(url);
    }

    public void pleaseWaitElement(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void pleaseWaitElements(WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
        //LOGGER.info("Click on " + locator);
    }

    public String getText(By locator) {
        //LOGGER.info("Getting text from " + locator );
        return driver.findElement(locator).getText();
    }

    public void pleaseAssert(String expectedText, String textFromWeb) {
        LOGGER.info("Assertion - " + "Expected: " + expectedText + " & Actual: " + textFromWeb);
        Assertions.assertEquals(expectedText, textFromWeb, "Asserting is failed");
    }

    public void comparePageTitle(String expectedText, String textFromWeb) {
        if (Objects.equals(expectedText, textFromWeb)) {
            LOGGER.info("The assertion passed - " + "Expected: " + expectedText + " & Actual: " + textFromWeb);
        } else {
            LOGGER.info("! ! ! THE ASSERTION FAILED - " + "Expected: " + expectedText + " & Actual: " + textFromWeb);
        }
    }

    public WebElement findElement(By locator) {
        //LOGGER.info("Getting element by " + locator);
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        //LOGGER.info("Getting all elements by " + locator);
        //LOGGER.info("List size is: " + driver.findElements(locator).size());
        return driver.findElements(locator);
    }

    public void typeIn(By locator, String text) {
        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(text);
    }

    public void closeBrowser() {
        LOGGER.info("Test done!");
        driver.quit();
    }

    public void refreshPage() {
        LOGGER.info("Refreshing the page");
        driver.navigate().refresh();
    }

    public void loadingAllResults(By locator, By waitingLocator) {
        driver.findElement(locator).click();
        pleaseWaitElement(waitingLocator);
    }

    public void selectByValue(By locator, String value) {
        Select dropDown = new Select(driver.findElement(locator));
        dropDown.selectByValue(value);
    }

    public void selectByIndex(By locator, Integer index) {
        Select dropDown = new Select(driver.findElement(locator));
        dropDown.selectByIndex(index);
    }

    public void switchToNextTab() {
            // Store all currently open tabs in tabs
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

            driver.switchTo().window(tabs.get(1));
//            driver.close();
//            driver.switchTo().window(tabs.get(0));
        LOGGER.info("We switched to the next tab");

    }


}
