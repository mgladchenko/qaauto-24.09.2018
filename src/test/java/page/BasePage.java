package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * Base page object for all pages.
 */
public abstract class BasePage {
    WebDriver webDriver;
    static GMailService gMailService;

    public void waitUntilElementIsClickable(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public abstract boolean isPageLoaded();

}
