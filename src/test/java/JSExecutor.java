import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class JSExecutor
{
    WebDriver driver;


    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1295, 800));
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://p134poznan.szkolnastrona.pl/");
        //JavascriptExecutor js;
        //js = (JavascriptExecutor) driver;
    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void executeMouseover()
    {
        Actions actions = new Actions(driver);
        WebElement menuItem = driver.findElement(By.cssSelector("div:nth-child(1) ul.tm_menu.topMenu.menus.dropdown-0 li:nth-child(4) a:nth-child(1) > span.menuF"));
        actions.moveToElement(menuItem).perform();
        WebElement hoverItems = driver.findElement(By.cssSelector("li.hover"));
        Assertions.assertTrue(hoverItems.isDisplayed(), "'Hover items' are not displayed");
    }

    /* @Test
    public void promptBoxTest()
    {
        String javascript = "prompt('Możesz tutaj coś wpisać')";
        js.executeScript(javascript);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        js.executeScript(javascript);
        driver.switchTo().alert().dismiss();

    } */
}
