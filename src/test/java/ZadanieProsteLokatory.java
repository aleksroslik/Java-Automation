import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZadanieProsteLokatory
{
    WebDriver driver;

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void prosteLokatory() throws InterruptedException {
        //1. Znajdz okienko "Szukaj produktów".

        driver.findElement(By.id("woocommerce-product-search-field-0"));

        //2. Znajdz pole do wpisania nazwy uzytkownika.

        driver.findElement(By.id("username"));
        driver.findElement(By.cssSelector("#reg_email[name='email']"));

        //3. Znajdz pole do wpisania hasla.

        driver.findElement(By.xpath(".//input[@autocomplete='current-password']"));

        //4. Zlokalizuj przycisk do logowania.

        driver.findElement(By.name("login"));

        //5. Zlokalizuj check box do zapamietania hasla

        driver.findElement(By.name("rememberme"));

        //6. Znajdz link do odzyskiwania hasla

        driver.findElement(By.linkText("Nie pamiętasz hasła?"));

        //7. Link do kategorii "zeglarstwo"

        driver.findElement(By.linkText("Żeglarstwo"));

        //znajdz link do strony głownej i przejdz do strony glownej

        driver.findElement(By.cssSelector("[id='menu-item-197'] [href='https://fakestore.testelka.pl']")).click();

        driver.findElement(By.xpath(".//a[@href='?add-to-cart=4116']"));

    }
}
