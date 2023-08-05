package qa.demo.tests.owner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebDriverNotOptimizedTest {


    @Test
    //не оптимизированный тест, выполнять без BeforeEach и AfterEach
    public void testGithub1() {
        // подготовка драйвера
        WebDriverManager.chromedriver().setup(); // подтягивает актуальную версию браузера, без нее тест упадет
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com");

        // тело выполнения теста
        String title = driver.getTitle();
        assertEquals("GitHub: Let’s build from here · GitHub", title);
        driver.quit();
    }

    @Test
    //тест с getDriver(), выполнять без BeforeEach и AfterEach. С getDriverNotOptimized()
    public void testGithub2() {
        WebDriver driver = getDriverNotOptimized();
        driver.get("https://github.com");
        String title = driver.getTitle();
        assertEquals("GitHub: Let’s build from here · GitHub", title);
        driver.quit();
    }

    @Test
    //тест с getDriver(), выполнять без BeforeEach и AfterEach. С getDriver()
    public void testGithub3() {
        WebDriver driver = getDriver();
        String title = driver.getTitle();
        assertEquals("GitHub: Let’s build from here · GitHub", title);
        driver.quit();
    }

    //конфигурация
    private WebDriver getDriverNotOptimized() {
        WebDriverManager.firefoxdriver().setup(); // подтягивает актуальную версию браузера, без нее тест упадет
        return new FirefoxDriver();
    }

    //конфигурация
    private WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup(); // подтягивает актуальную версию браузера, без нее тест упадет
        WebDriver driver = new ChromeDriver();
        driver.get("https://github.com");
        return driver;
    }

}
