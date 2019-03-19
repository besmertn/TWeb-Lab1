import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TutByTest {

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
    public void TestAuthorization() {
        driver.navigate().to("https://www.tut.by");
        driver.switchTo().frame(driver.findElement(By.id("top_bar_helper")));
        driver.findElement(By.cssSelector("#authorize > div > a")).click();
        driver.findElement(By.cssSelector("form.auth-form input[name='login']")).sendKeys("login");
        driver.findElement(By.cssSelector("form.auth-form input[name='password']")).sendKeys("password");
        //driver.findElement(By.cssSelector("form.auth-form input[type='submit']")).click();
        driver.findElement(By.cssSelector(".b-auth-soc .social__btn--fb")).click();
        WebElement userName = driver.findElement(By.cssSelector("#authorize .uname"));
        assert userName.getText().equals("Александр Бессмертный");
    }
}
