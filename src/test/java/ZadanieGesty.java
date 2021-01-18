import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZadanieGesty {

    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/actions/");
        actions = new Actions(driver);
    }

    @AfterEach
    public void driverQuit()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void goToCartTest()
    {
        WebElement menu = driver.findElement(By.cssSelector("#menu-link"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
        actions.contextClick(menu).build().perform();
        WebElement cart = driver.findElement(By.cssSelector(".menu-cart"));
        actions.click(cart).build().perform();
        Assertions.assertEquals("https://fakestore.testelka.pl/koszyk/", driver.getCurrentUrl(), "The URL is now that expected");
    }
    @Test
    public void doubleClick()
    {
        WebElement colorBox = driver.findElement(By.cssSelector("#double-click"));
        actions.doubleClick(colorBox).build().perform();
        Assertions.assertEquals("rgba(255, 204, 0, 1)", colorBox.getCssValue("background-color"), "the color is not correct");
    }

    @Test
    public void text()
    {
        WebElement input = driver.findElement(By.cssSelector("#input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", input);
        actions.sendKeys(input, "test").build().perform();
        WebElement button = driver.findElement(By.cssSelector("[onclick='zatwierdzTekst()']"));
        actions.click(button).build().perform();
        WebElement output = driver.findElement(By.cssSelector("#output"));
        Assertions.assertEquals("Wprowadzony tekst: test", output.getText(), "the text is not what expected");
    }

    @Test
    public void select()
    {
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-selectee"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", items.get(0));
        actions.keyDown(Keys.CONTROL).click(items.get(3)).click(items.get(7)).click(items.get(10)).keyUp(Keys.CONTROL).build().perform();

    }
}
