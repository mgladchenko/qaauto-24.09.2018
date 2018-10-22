import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver webDriver;

    private WebElement profileNavItem;

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && webDriver.getTitle().contains("LinkedIn")
                && profileNavItem.isDisplayed();
    }

    private void initElements() {
        profileNavItem = webDriver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

}
