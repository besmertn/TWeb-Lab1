package pages;

import models.User;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private static String URL_MATCH = "registration";

    private WebDriver driver;

    @FindBy(id = "id-login")
    private WebElement loginField;

    @FindBy(id = "id-password")
    private WebElement passwordField;

    @FindBy(id = "id-password-repeat")
    private WebElement passwordRepeatField;

    @FindBy(id = "id-first-name")
    private WebElement firstNameField;

    @FindBy(css = "input[tabindex='5']")
    private WebElement secondNameField;

    @FindBy(id = "id-birth-day")
    private WebElement birthDayField;

    @FindBy(css = ".input-select__target")
    private WebElement birthMonthDropdown;

    @FindBy(css = "input[tabindex='8']")
    private WebElement birthYearField;

    @FindBy(id = "id-sex-m")
    private WebElement sexMRadio;

    @FindBy(id = "id-sex-f")
    private WebElement sexFRadio;

    @FindBy(id = "id-mobile")
    private WebElement mobileField;

    @FindBy(id = "id-confirm-privacy")
    private WebElement confirmPrivacyCheckbox;

    @FindBy(css = "button.submit")
    private WebElement submitButton;

    public RegistrationPage(WebDriver driver) throws InterruptedException {
        this.driver = driver;
        waitForLoad();
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }

        PageFactory.initElements(driver, this);
    }

    public void EnterAllDataAndNotConfirmPhone(User user) throws InterruptedException {
        loginField.sendKeys(user.getLogin());
        passwordField.sendKeys(user.getPassword());
        passwordRepeatField.sendKeys(user.getPassword());
        firstNameField.sendKeys(user.getFirstName());
        secondNameField.sendKeys(user.getFirstName());
        birthDayField.sendKeys(Integer.toString(user.getBirthDate().getDay()));
        birthMonthDropdown.click();

        driver.findElements(By.cssSelector("ul.input-select__list > li")).get(user.getBirthDate().getMonth()).click();

        if(user.getGender().equals("male")) {sexMRadio.click();}
        else {sexFRadio.click();}

        mobileField.sendKeys(user.getPhone());

        confirmPrivacyCheckbox.click();
        Thread.sleep(5000);
        Assert.assertFalse(submitButton.isEnabled());
    }

    public void waitForLoad() throws InterruptedException {
        Thread.sleep(1000);
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
}
