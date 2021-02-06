import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Testy1
{
    WebDriver driver;

    @BeforeEach
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
    }

    @AfterEach
    public void Close()
    {
        driver.close();
    }

    @Test
    public void demoTest()
    {
        driver.get("https://www.google.pl");
        driver.navigate().to("https://amazon.co.uk");
        driver.navigate().back();
        driver.navigate().forward();

    }

    @Test
    public void pageSource()
    {
        String wikiLanguage = "lang=\"pl\"";
        driver.navigate().to("https://www.wikipedia.pl");
        Assertions.assertTrue(driver.getPageSource().contains(wikiLanguage), "page does not contain" + wikiLanguage);

    }

}
