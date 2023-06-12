package qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HerokuAppTest {
    Logger logger = LoggerFactory.getLogger(HerokuAppTest.class);
    static final Logger LOGGER = LoggerFactory.getLogger(HerokuAppTest.class);
    //работает исправно
    @Test
    void dragAndDropBySelenideElement() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $x("//*[@id='column-a']").shouldHave(text("A"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $x("//*[@id='column-a']").shouldHave(text("B"));
    }

    //отработал корректно один раз, после чего постоянно падает
    @Test
    void dragAndDropByAction() {
        logger.info("Start Test");
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $x("//*[@id='column-a']").shouldHave(text("B"));
        Selenide.actions().dragAndDrop($("#column-a"),$("#column-b"));
        $x("//*[@id='column-a']").shouldHave(text("A"));
        logger.info("Finish Test");
    }
}
