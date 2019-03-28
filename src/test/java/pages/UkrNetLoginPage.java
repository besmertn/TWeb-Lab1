package pages;

import models.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

public class UkrNetLoginPage {
    private static String URL_MATCH = "login";

    private WebDriver driver;

    @FindBy(id = "id-l")
    private WebElement login;

    @FindBy(id = "id-p")
    private WebElement password;

    @FindBy(id = "id-с")
    private WebElement publicComputerCheckbox;

    @FindBy(css = "button[type=submit]")
    private WebElement submitButton;

    @FindBy(css = ".form__controls a:last-child")
    private WebElement registerLink;


    public UkrNetLoginPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void LoginUser(User user) throws InterruptedException {
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());

        Thread.sleep(1000);
        submitButton.click();
        try {
            CaptchaPage captcha = new CaptchaPage(driver);
            captcha.EnterCaptcha();
        }
        catch (NoSuchElementException e) {}
        finally {
            submitButton.click();
        }
    }

    public void LoginUserPublicComputer(User user) throws InterruptedException {
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());

        publicComputerCheckbox.click();
        Thread.sleep(1000);
        submitButton.click();
        try {
            CaptchaPage captcha = new CaptchaPage(driver);
            captcha.EnterCaptcha();
        }
        catch (NoSuchElementException e) {}
        finally {
            submitButton.click();
        }
    }
}