import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.MailBoxPage;
import pages.RegistrationPage;
import pages.UkrNetLoginPage;

import java.io.IOException;

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
        loginPage.LoginUserSuccess(user);
    }

    @Test
    public void TestDeleteUnreadEmails() throws InterruptedException {
        driver.navigate().to("https://accounts.ukr.net/login");
        UkrNetLoginPage loginPage = new UkrNetLoginPage(driver);
        User user = User.CreateExistingUser();

        MailBoxPage mailBoxPage = loginPage.LoginUserSuccess(user);
        mailBoxPage.DeleteUnreadEmails();
        mailBoxPage.ExitMailBox();
    }

    @Test
    public void TestLOginError() throws InterruptedException {
        driver.navigate().to("https://accounts.ukr.net/login");
        UkrNetLoginPage loginPage = new UkrNetLoginPage(driver);
        User user = User.CreateNotExistingUser();

        loginPage = loginPage.LoginUserError(user);
        loginPage.CheckErrorMessage("Неправильні дані");

    }

    @Test
    public void TestRegistrationWIthoutPhoneVerification() throws InterruptedException {
        driver.navigate().to("https://accounts.ukr.net/login");
        UkrNetLoginPage loginPage = new UkrNetLoginPage(driver);
        User user = User.CreateValidUser();

        RegistrationPage regPage = loginPage.GoToRegistration();

        regPage.EnterAllDataAndNotConfirmPhone(user);
    }

    @Test
    public void TestNewEmails() {
        try {
            final WebClient webClient = new WebClient();
            webClient.setJavaScriptErrorListener(null);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            final HtmlPage loginPage = webClient.getPage("https://accounts.ukr.net/login");

            /*final HtmlForm form = loginPage.getForms().get(0);

            final HtmlElement userName = form.getInputByName("username");
            userName.type("besmertn");

            form.getInputByName("password").type("123456");

            HtmlPage indexPage = form.getButtonByName("submit").click();

            String title = indexPage.getHead().getEnclosingElement("tittle").getTextContent();
            System.out.println(title);*/
            loginPage.getElementById("id-l").setAttribute("value", "besmertn");
            loginPage.getElementById("id-p").setAttribute("value", "56445556");

            final HtmlElement submitButton = (HtmlElement)loginPage.getByXPath("button[type=submit]").get(0);
            final HtmlPage mailBoxPage = submitButton.click();

            String title = mailBoxPage.getHead().getEnclosingElement("tittle").getTextContent();
            System.out.println(title);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
