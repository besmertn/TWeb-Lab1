package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage {

    private static String URL_MATCH = "msglist";

    private WebDriver driver;

    @FindBy(id = "FILTER-unread")
    private WebElement unreadFolder;

    @FindBy(id = "global_marker")
    private WebElement selectAllCheckbox;

    @FindBy(css = ".delete a")
    private WebElement deleteButton;

    @FindBy(css = ".exit a")
    private WebElement exitButton;


    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        waitForLoad();

        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
    }

    public void DeleteUnreadEmails() {
        unreadFolder.click();
        selectAllCheckbox.click();
        deleteButton.click();
    }

    public UkrNetLoginPage ExitMailBox() {
        exitButton.click();
        return new UkrNetLoginPage(driver);
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
