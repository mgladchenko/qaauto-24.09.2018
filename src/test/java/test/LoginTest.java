package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;

public class LoginTest extends BaseTest {

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
                "page.HomePage is not displayed on Login page.");
    }

    @Test
    public void negativeLoginWithEmptyPasswordTest(){
        //ToDo: Fixme
        //Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
                //"Login page URL is wrong.");

        loginPage.login("a@b.c", "");

        //Assert.assertEquals(webDriver.getCurrentUrl(), "https://www.linkedin.com/",
               // "Login page URL is wrong.");
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
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "page.LoginSubmitPage page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(),
                "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message text is wrong.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(), emailValidationMessage,
                "Email validation message is wrong.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(), passwordValidationMessage,
                "Password validation message is wrong.");






    }
}
