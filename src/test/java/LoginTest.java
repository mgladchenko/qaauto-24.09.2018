import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    /**
     * PreConditions:
     * - Open FF browser.
     *
     * Scenario:
     * - Navigate to https://linkedin.com.
     * - Verify that Login page is loaded.
     * - Enter userEmail into userEmail field.
     * - Enter userPassword into userPassword field.
     * - Click on signIn button.
     * - Verify that Home page is loaded.
     *
     * PostCondition:
     * - Close FF browser.
     */
    @Test
    public void successfulLoginTest() {
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");
        Assert.assertTrue(loginPage.signInButton.isDisplayed(),
                "SignInButton is not displayed on Login page.");

        loginPage.login("linkedin.tst.yanina@gmail.com", "Test123!");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/feed/",
                "Home page URL is wrong.");
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn",
                "Home page title is wrong.");

        HomePage homePage = new HomePage(webDriver);
        Assert.assertTrue(homePage.profileNavItem.isDisplayed(),
                "profileNavItem is not displayed on Login page.");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");

        loginPage.login("a@b.c", "");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");
    }

    @Test
    public void negativeLoginWithWrongPasswordTest(){
        webDriver.get("https://linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");

        loginPage.login("linkedin.tst.yanina@gmail.com", "wrong");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login-Submit page URL is wrong.");
    }
}
