package qa.demo.tests.jenkins.simple;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import qa.demo.helpers.Attach;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseRemoteTest {

    @BeforeAll
    static void preconditionConfiguration() {
        String browser = System.getProperty("browser", "chrome");
        String baseUrl = System.getProperty("baseUrl");
        String browserSize = System.getProperty("browserSize", "1920x1080");
        String remote = System.getProperty("remote");
        String browserVersion = System.getProperty("browserVersion");

        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = baseUrl;
        Configuration.browserSize = browserSize;
        Configuration.holdBrowserOpen = false;
        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion;
        //в начале урла добавляем "логин:пароль@", в конце урла добавляем "wd/hub"
        Configuration.remote = remote;

        /* enableVNC нужно для того чтобы мы видели, что происходит на экране
                удаленного браузера во время его работы. Без нее будет просто черный экран*/
        /* enableVideo позволяет нам записывать видео прогона тестов*/
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.of(
                "enableVNC", true,
                "enableVideo", true
        ));
        //подключаем capabilities
        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();

        closeWebDriver();
    }

    public void openPage(String pageURL) {
        open(pageURL);
    }
}
