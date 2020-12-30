import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ZadanieMetodyNaElementach {

    WebDriver driver;
    By demoStoreBar = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1295, 730));
        driver.manage().window().setPosition(new Point(10,40));
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.navigate().to("https://fakestore.testelka.pl/metody-na-elementach");
    }

    @AfterEach
    public void Close()
    {
        driver.close();
        driver.quit();
    }

    @Test
    public void checkElementStateTest()
    {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement sailingButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckBox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement notSelectedCheckBox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement selectedRadioButton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelectedRadioButton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> elementsWithButtonClass = driver.findElements(By.cssSelector(".button"));

        Assertions.assertAll("Checking properties of ELements",
                ()->Assertions.assertFalse(mainPageButton.isEnabled(), "Main Page button is not disabled"),
                ()->Assertions.assertFalse(sailingButton.isDisplayed(), "'Sailing Button is properly displayed"),
                ()->assertThatButtonsAreYellow(yellowButtons),
                ()->Assertions.assertTrue(selectedCheckBox.isSelected(), "Checkbox is not selected"),
                ()->Assertions.assertTrue(selectedRadioButton.isSelected(), "Rario Button is not selected"),
                ()->Assertions.assertFalse(notSelectedCheckBox.isSelected(), "Checkbox is selected"),
                ()->Assertions.assertFalse(notSelectedRadioButton.isSelected(), "Rario Button is selected"),
                ()->assertElementsHaveCorrectTag(elementsWithButtonClass)
        );
    }

    public void assertThatButtonsAreYellow(List<WebElement> buttons)
    {
        for (WebElement button:buttons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)", color, "Button color is not what expected");
        }
    }

    public void assertElementsHaveCorrectTag(List<WebElement> elements) {
        for (WebElement element:elements) {
            Assertions.assertEquals("a",element.getTagName(), "Element's tag is not what expected");
        }
    }
}
