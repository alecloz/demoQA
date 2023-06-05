package qa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HerokuAppTest {

    //работает исправно
    @Test
    void dragAndDropBySelenideElement() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $x("//*[@id='column-a']").shouldHave(text("A"));
        $("#column-a").dragAndDropTo( $("#column-b"));
        $x("//*[@id='column-a']").shouldHave(text("B"));
    }

    //отработал корректно один раз, после чего постоянно падает
    /*@Test
    void dragAndDropByAction() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebDriverRunner.getWebDriver().manage().window().maximize();
        $x("//*[@id='column-a']").shouldHave(text("B"));
        Selenide.actions().dragAndDrop($("#column-a"),$("#column-b"));
        $x("//*[@id='column-a']").shouldHave(text("A"));
    }*/
}
