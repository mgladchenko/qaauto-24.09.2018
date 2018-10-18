import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {

    private WebDriver webDriver;

    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public boolean isPageLoaded() {
        return webDriver.getCurrentUrl().equals("https://www.linkedin.com/")
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && isSignInButtonDisplayed();
    }

    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

    private void initElements() {
        userEmailField = webDriver.findElement(By.id("login-email"));
        userPasswordField = webDriver.findElement(By.id("login-password"));
        signInButton = webDriver.findElement(By.id("login-submit"));
    }

    public void login(String userEmail, String userPassword){
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
    }
}
