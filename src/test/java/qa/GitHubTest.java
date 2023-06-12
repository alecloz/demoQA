package qa;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTest {

    @Test
    void checkDisplayingEnterprisePage() {
        open("https://github.com");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $x("//*[contains(text(),'Solutions')]").hover();
        $x("//nav//a[contains(text(),'Enterprise')]").click();
        $x("//a[starts-with(@href, '/features/preview')]/../h1").shouldHave(text("Build like the best"));
    }
}