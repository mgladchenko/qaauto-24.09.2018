import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmit {
    private WebDriver webDriver;

    @FindBy(xpath = "//*")
    private WebElement dummyElement;

    public LoginSubmit(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("uas/login-submit")
                && webDriver.getTitle().contains("Sign In to LinkedIn")
                && dummyElement.isDisplayed();
    }

}
