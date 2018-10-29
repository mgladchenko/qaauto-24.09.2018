import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSubmitPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertBox;

    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement emailValidationMessage;

    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement passwordValidationMessage;


    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().contains("uas/login-submit")
                && webDriver.getTitle().contains("Sign In to LinkedIn")
                && alertBox.isDisplayed();
    }

    public String getAlertMessageText() {
        return alertBox.getText();
    }

    public String getEmailValidationMessage() {
        return emailValidationMessage.getText();
    }

    public String getPasswordValidationMessage() {
        return passwordValidationMessage.getText();
    }
}
