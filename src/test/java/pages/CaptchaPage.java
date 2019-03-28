package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

class CaptchaPage {
    private WebDriver driver;

    @FindBy(css = "iframe[role=presentation]")
    private WebElement capchaFrame;

    @FindBy(css = ".recaptcha-checkbox-checkmark")
    private WebElement capchaCheckbox;

    CaptchaPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        try {
            driver.switchTo().frame(capchaFrame);
        }
        catch (NoSuchElementException e) {
            throw new NoSuchElementException(e.getMessage());
        }

    }

    void EnterCaptcha() {
        capchaCheckbox.click();
        driver.switchTo().defaultContent();
    }
}
