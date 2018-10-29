import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage = new LoginPage(webDriver);

    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][] {
                { "linkedin.tst.yanina@gmail.com", "Test123!"},
                { "linkedin.TST.yanina@gmail.com", "Test123!"},
                { " linkedin.tst.yanina@gmail.com ", "Test123!"}
        };
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
    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login( userEmail, userPassword);

        Assert.assertTrue(homePage.isPageLoaded(),
                "HomePage is not displayed on Login page.");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest(){
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");

        loginPage.login("a@b.c", "");

        Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                "Login page URL is wrong.");
    }

    @DataProvider
    public Object[][] validationMessagesCombinations() {
        return new Object[][] {
                { "linkedin.tst.yanina@gmail.com", "wrong", "", "The password you provided must have at least 6 characters."},

        };
    }

    @Test(dataProvider = "validationMessagesCombinations")
    public void validationMessagesOnInvalidEmailPasswordTest(String userEmail,
                                                        String userPassword,
                                                        String emailValidationMessage,
                                                        String passwordValidationMessage){
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage = loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmitPage page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message text is wrong.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(), emailValidationMessage,
                "Email validation message is wrong.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(), passwordValidationMessage,
                "Password validation message is wrong.");






    }
}
