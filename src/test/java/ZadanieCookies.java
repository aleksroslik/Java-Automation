import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class ZadanieCookies
{
    WebDriver driver;

    @BeforeEach
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1280, 720));
        driver.navigate().to("https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna");
    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void cookies()
    {
        //1. Pobierz wszystkie ciasteczka i przy pomocy asercji sprawdź, czy jest ich tyle ile powinno.
        Set<Cookie> cookies = driver.manage().getCookies();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Assertions.assertEquals(4, driver.manage().getCookies().size(), "the number of cookies is not " + cookies);

        //2. Dodaj swoje ciasteczko i potwierdź asercją, że się dodało.
        Cookie newCookie = new Cookie("test_cookie", "test_value");
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(5, driver.manage().getCookies().size(), "the number of cookies is not " + cookies);

        //3. Pobierz swoje ciasteczko i użyj asercji, żeby porównać, że nazwa ciasteczka jest taka, jakiej oczekujesz.
        Assertions.assertEquals(newCookie.getName(), driver.manage().getCookieNamed("test_cookie").getName(),"name of the cookie does not equal" + newCookie);
        
        //4. Usuń swoje ciasteczko używając obiektu typu Cookie jako parametru i potwierdź, że zostało usunięte.
        driver.manage().deleteCookieNamed("test_cookie");
        Assertions.assertEquals(4, driver.manage().getCookies().size(), "the number of cookies is not " + cookies);

    }

}

