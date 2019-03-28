package pages;

import models.User;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        this.driver = driver;
        waitForLoad();
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
    }

    public MailBoxPage LoginUser(User user) throws InterruptedException {
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());

        //Thread.sleep(1000);
        submitButton.click();
        try {
            CaptchaPage captcha = new CaptchaPage(driver);
            captcha.EnterCaptcha();
            submitButton.click();
        } catch (NoSuchElementException ignored) {}
        Thread.sleep(1000);
        return new MailBoxPage(driver);
    }

    public MailBoxPage LoginUserPublicComputer(User user) throws InterruptedException {
        login.sendKeys(user.getLogin());
        password.sendKeys(user.getPassword());

        publicComputerCheckbox.click();
        //Thread.sleep(1000);
        submitButton.click();
        try {
            CaptchaPage captcha = new CaptchaPage(driver);
            captcha.EnterCaptcha();
            submitButton.click();
        } catch (NoSuchElementException ignored) {}

        return new MailBoxPage(driver);
    }

    public void waitForLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}