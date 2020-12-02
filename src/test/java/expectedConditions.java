import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.concurrent.TimeUnit;

public class expectedConditions
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/product/grecja-limnos/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        wait = new WebDriverWait(driver,5);
    }

    @AfterEach
    public void Close()
    {
        driver.quit();
    }

    @Test
    public void waitExample()
    {
        driver.findElement(By.cssSelector("[name='add-to-cart']")).click();
        driver.findElement(By.cssSelector(".woocommerce-message .wc-forward")).click();

        WebElement quantityField = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='quantity']")));

        quantityField.clear();
        quantityField.sendKeys("2");

        driver.findElement(By.cssSelector("[name='update_cart']")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".blockUI"),0));

        String amount = driver.findElement(By.cssSelector(".order-total .amount")).getText();
        String expected = "6 400,00 zł";
        Assertions.assertEquals(expected, amount, "not equal");

    }
}
