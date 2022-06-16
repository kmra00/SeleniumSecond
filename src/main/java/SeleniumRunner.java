import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;

public class SeleniumRunner {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String fraseBusqueda = "selenium";
        driver.get("https://github.com");
        WebElement searchInput = driver.findElement(By.cssSelector("[name='q']"));

        searchInput.sendKeys(fraseBusqueda);
        searchInput.sendKeys(Keys.ENTER);

        List<String> actualItems = driver.findElements(By.cssSelector("repo-list-item"))
                .stream()
                .map(element -> element.getText().toLowerCase())
                .collect(Collectors.toList());
        //  Assert.assertTrue(actualItems.stream().allMatch(item -> item.contains("selenium")));

        List<String> expectedItems = actualItems.stream()
                .filter(item -> item.contains(fraseBusqueda))
                .collect(Collectors.toList());

        Assert.assertEquals(expectedItems, actualItems);
        driver.quit();
    }
}
