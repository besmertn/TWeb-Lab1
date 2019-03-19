import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TutByTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        String pathToChromeDriver = "lib/chromedriver";
        System.setProperty("web.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.close();
    }

    @Test
    public void TestAuthorization() {
        driver.navigate().to("https://www.tut.by");
    }
}
