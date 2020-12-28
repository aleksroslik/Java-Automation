import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ZadanieKupony
{
    WebDriver driver;
    WebDriverWait wait;
    By cookieConsentBar = By.cssSelector(".woocommerce-store-notice__dismiss-link");
    By pilatesGroup = By.cssSelector("a[href*='pilates']");
    By product = By.cssSelector("li.post-61");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a.cart-contents");
    String correctCoupon = "10procent";
    String incorrectCoupon = "test";

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 760));
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl");

        wait = new WebDriverWait(driver,10);

        driver.findElement(cookieConsentBar).click();
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();
        driver.findElement(addToCartButton).click();
        driver.findElement(goToCartButton).click();
    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void emptyCouponTest()
    {
        applyCoupon("");
        Assertions.assertEquals(getAlertText(), "Proszę wpisać kod kuponu.", "Alert message was not what expected");
    }

    @Test
    public void incorrectCouponTest()
    {
        applyCoupon(incorrectCoupon);
        Assertions.assertEquals(getAlertText(), "Kupon \"test\" nie istnieje!", "Alert message was not what expected");
    }

    @Test
    public void correctCouponTest()
    {
        applyCoupon(correctCoupon);
        Assertions.assertEquals(getAlertText(), "Kupon został pomyślnie użyty.", "Alert message was not what expected");
    }

    @Test
    public void addingCouponWhenAlreadyAppliedTest()
    {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        Assertions.assertEquals(getAlertText(), "Kupon został zastosowany!", "Alert message was not what expected");
    }

    @Test
    public void removingCouponTest()
    {
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        By removeLink = By.cssSelector("a.woocommerce-remove-coupon");
        wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
        waitForProcessingEnd();
        Assertions.assertEquals(getAlertText(), "Kupon został usunięty.", "Alert message was not what expected");
    }

    private void applyCoupon(String coupon) {
        By couponCodeField = By.cssSelector("[name='coupon_code']");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        driver.findElement(couponCodeField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
    }

    private void waitForProcessingEnd() {
        By blockedUI = By.cssSelector("div.blockUI");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI, 0));
    }

    private String getAlertText() {
        By alert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }
}

