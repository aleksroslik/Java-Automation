import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Interakcje {

    WebDriver driver;

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }

    @AfterEach
    public void Close()
    {
        driver.quit();
    }

    @Test
    public void Wishlist()
    {
        driver.navigate().to("https://fakestore.testelka.pl/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();
        driver.findElement(By.xpath("//body/div[@id='page']/div[@id='content']/div[1]/div[2]/main[1]/section[2]/div[1]/ul[1]/li[4]/a[1]/h2[1]")).click();
        driver.findElement(By.cssSelector("[data-title='Dodaj do listy życzeń']")).click();
        driver.findElement(By.cssSelector("span.feedback"));

    }

    @Test
    public void Zooniverse()
    {
        driver.navigate().to("https://www.zooniverse.org/");
        driver.findElement(By.xpath("//header/nav[1]/span[2]/button[1]/span[1]/span[1]")).click();
        driver.findElement(By.cssSelector("[name='login']")).sendKeys("malaMi");
        driver.findElement(By.cssSelector("[name='password']")).sendKeys("hasłotestowe");
        driver.findElement(By.xpath("//body/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/form[1]/button[1]")).submit();
    }

    @Test
    public void fileUpload()
    {
        driver.navigate().to("https://gofile.io/uploadFiles");
        WebElement uploadFileInput = driver.findElement(By.cssSelector("input[type='file']"));
        String path = "C:\\Users\\Rossliczq\\Desktop\\dziurawe_owoce_ZOSIA_R.jpg";
        uploadFileInput.sendKeys(path);
        String actualFileName = driver.findElement(By.xpath("//td[contains(text(),'dziurawe_owoce_ZOSIA_R.jpg')]")).getText();
        String expectedFileName = "dziurawe_owoce_ZOSIA_R.jpg";
        Assertions.assertEquals(expectedFileName, actualFileName, "not equal");
    }
}
