package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void successfulResetPasswordTest() throws InterruptedException {
        String newPassword = "Test123!";

        Assert.assertTrue(loginPage.isPageLoaded(),
                "page.LoginPage is not loaded.");

        RequestPasswordResetPage requestPasswordResetPage =
                loginPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isPageLoaded(),
                "page.RequestPasswordResetPage is not loaded.");

        PasswordResetSubmitPage passwordResetSubmitPage =
                requestPasswordResetPage.findAccount("linkedin.tst.yanina@gmail.com");


        sleep(180000);
        Assert.assertTrue(passwordResetSubmitPage.isPageLoaded(),
                "page.PasswordResetSubmitPage is not loaded.");

        SetNewPasswordPage linkedinSetNewPasswordPage =
                passwordResetSubmitPage.navigateToLinkFromEmail();
        Assert.assertTrue(linkedinSetNewPasswordPage.isLoaded(),
                "page.SetNewPasswordPage is not loaded.");

        SuccessfulPasswordResetPage successfulPasswordResetPage =
                linkedinSetNewPasswordPage.submitNewPassword(newPassword);
        Assert.assertTrue(successfulPasswordResetPage.isLoaded(),
                "page.SuccessfulPasswordResetPage is not loaded.");

        HomePage homePage =
                successfulPasswordResetPage.clickOnGoToHomeButton();
        //sleep(180000);
        Assert.assertTrue(homePage.isPageLoaded(),
                "page.HomePage is not loaded.");


    }
}
