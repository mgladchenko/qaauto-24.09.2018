package page;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    WebDriver webDriver;

    public abstract boolean isPageLoaded();

}
