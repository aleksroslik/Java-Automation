import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SwitchingWindows
{
    WebDriver driver;
    WebDriverWait wait;
    By cookieAccept = By.cssSelector("#cn-accept-cookie");

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10, 40));
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.navigate().to("https://testelka.pl/blog/");
        wait = new WebDriverWait(driver, 5);
        driver.findElement(cookieAccept).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cookieAccept));

    }
    @AfterEach
    public void driverQuit()
    {
        driver.close();
        driver.quit();
    }
    @Test
    public void windowHandlesTest(){
        driver.findElement(By.cssSelector("#custom_html-49 > div > div > a:nth-child(2)")).click(); //zamykamy cookie disclaimer
        Set<String> windows = driver.getWindowHandles(); //pobieram id otwartych okien
        String parentWindow = driver.getWindowHandle(); // pobieram id okna głownego
        windows.remove(parentWindow); // usuwa id okna glownego z variables
        String secondWindow = windows.iterator().next(); // dodajemy info do variables
        driver.switchTo().window(secondWindow); // przelaczam sie na drugie okno
        String activeWindow = driver.getWindowHandle(); //pobiera id dla aktywnego okna
        driver.findElement(By.cssSelector("#logo-icon-container")).click(); //asercja ze udalo sie wykonac akcje w aktywnym oknie po przelaczeniu
        driver.switchTo().window(parentWindow); // wracam do aktywnego okna
    }
}
