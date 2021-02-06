import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ZadanieProsteLokatory
{
    WebDriver driver;

    @BeforeEach
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void prosteLokatory()
    {

        driver.navigate().to("http://fakestore.testelka.pl/moje-konto/");
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

    @Test
    public void lokatoryCD()
    {
        driver.navigate().to("https://fakestore.testelka.pl/cwiczenia-z-selektorow-fragmenty-wartosci-atrybutow/");
        //1. Button1, Button 2, Btn 3, Btn 4
        driver.findElements(By.cssSelector("a.button"));
        //2. Btn 3, Btn 4, Btn 7
        driver.findElements(By.cssSelector("[id^='btn-']"));
        //3. Btn 3, Btn 7
        driver.findElements(By.partialLinkText("style*='background-color:#db456f']"));
        //4. Button1, Button 2, Button 5
        driver.findElements(By.cssSelector("[id^='button-']"));
        //5. Button1, Btn 3, Button 5
        driver.findElements(By.cssSelector(".accent"));
        //6. Button 2, Button6, Btn 7
        driver.findElements(By.cssSelector("[class='button primary test']"));
        //7. Button1, Btn 3, Btn 4, Button 5
        driver.findElements(By.cssSelector("[class^='button accent']"));
    }

}
