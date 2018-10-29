import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

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

    /**
     * PreConditions:
     * - Open new Browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that Login page is loaded.
     * - Login with valid credentials.
     * - Verify that Home page is loaded.
     * - Enter 'searchTerm' into search field and press RETURN key.
     * - Verify that Search page is loaded.
     * - Verify 10 searchResults displayed on page.
     * - Verify each result item contains 'searchTerm'
     *
     * PostConditions:
     * - Close Browser.
     */
    @Test
    public void basicSearchTest() {
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        HomePage homePage = loginPage.login("linkedin.tst.yanina@gmail.com", "Test123!");

        Assert.assertTrue(homePage.isPageLoaded(),
                "HomePage is not displayed on Login page.");
    }
}
