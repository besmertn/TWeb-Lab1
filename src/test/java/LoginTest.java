import models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MailBoxPage;
import pages.UkrNetLoginPage;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String pathToChromeDriver = "../../../chromedriver";
        System.setProperty("web.chrome.driver", pathToChromeDriver);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void TestAuthorization() throws InterruptedException {
        driver.navigate().to("https://accounts.ukr.net/login");
        UkrNetLoginPage loginPage = new UkrNetLoginPage(driver);
        User user = User.CreateExistingUser();
        loginPage.LoginUser(user);
    }

    @Test
    public void TestDeleteUnreadEmails() throws InterruptedException {
        driver.navigate().to("https://accounts.ukr.net/login");
        UkrNetLoginPage loginPage = new UkrNetLoginPage(driver);
        User user = User.CreateExistingUser();

        MailBoxPage mailBoxPage = loginPage.LoginUser(user);
        mailBoxPage.DeleteUnreadEmails();
        mailBoxPage.ExitMailBox();
    }
}
