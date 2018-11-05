import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class ResetPasswordTest {
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

    @Test
    public void successfulResetPasswordTest() throws InterruptedException {
        String newPassword = "Test123!";

        Assert.assertTrue(loginPage.isPageLoaded(),
                "LoginPage is not loaded.");

        RequestPasswordResetPage requestPasswordResetPage =
                loginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isLoaded(),
                "RequestPasswordResetPage is not loaded.");

        PasswordResetSubmitPage passwordResetSubmitPage =
                requestPasswordResetPage.findAccount("linkedin.tst.yanina@gmail.com");


        sleep(180000);
        Assert.assertTrue(passwordResetSubmitPage.isLoaded(),
                "PasswordResetSubmitPage is not loaded.");

        SetNewPasswordPage linkedinSetNewPasswordPage =
                passwordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),
                "SetNewPasswordPage is not loaded.");

        SuccessfulPasswordResetPage successfulPasswordResetPage =
                linkedinSetNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(successfulPasswordResetPage.isLoaded(),
                "SuccessfulPasswordResetPage is not loaded.");

        HomePage homePage =
                successfulPasswordResetPage.clickOnGoToHomeButton();
        //sleep(180000);
        Assert.assertTrue(homePage.isPageLoaded(),
                "HomePage is not loaded.");


    }
}
