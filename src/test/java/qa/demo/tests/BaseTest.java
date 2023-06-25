package qa.demo.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import qa.demo.pages.RegistrationPage;
import qa.demo.pages.TextBoxPage;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    RegistrationPage registratinPage = new RegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

    @BeforeAll
    static void preconditionConfiguration() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }
    public void openPage(String pageURL) {
        open(pageURL);
        adBlockAndFooterRemove();
    }

    public void adBlockAndFooterRemove() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}
