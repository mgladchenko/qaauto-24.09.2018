import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequestPasswordResetPage {
    private WebDriver webDriver;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userEmailField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isLoaded() {
        return findAccountButton.isDisplayed();
                //&& getCurrentTitle().equals("Reset Password | LinkedIn")
                //&& getCurrentUrl().contains("uas/request-password-reset");
    }

    public PasswordResetSubmitPage findAccount(String userEmail) {
        //gMailService.connect();

        userEmailField.sendKeys(userEmail);
        findAccountButton.click();

        return new PasswordResetSubmitPage(webDriver);
    }
}
