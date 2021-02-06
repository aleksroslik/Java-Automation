import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Ramki
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.navigate().to("https://www.nasa.gov/topics/history/index.html");

        wait = new WebDriverWait(driver, 5);

    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void frameExamples()
    {
        WebElement iFrame = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[@id='twitter-widget-0']")));
        //przelaczanie sie w kontekst ramki
        driver.switchTo().frame(iFrame);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'View on Twitter')]")));
        //przelaczanie sie z powrotem na glowna stronke
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".navbar-header>.logo")).click();
    }
}
