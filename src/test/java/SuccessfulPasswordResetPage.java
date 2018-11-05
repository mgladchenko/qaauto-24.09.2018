import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessfulPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goToHomepageButton;

    public SuccessfulPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public HomePage clickOnGoToHomeButton() {
        goToHomepageButton.click();
        return new HomePage(webDriver);
    }

    public boolean isLoaded() {
        return goToHomepageButton.isDisplayed();
    }
}
