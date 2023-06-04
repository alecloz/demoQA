package qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {
    @BeforeAll
    static void preconditionConfiguration() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "1024x768";
    }

    @Test
    void checkDisplayingJUnit5BlocksOfCode() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("a[href$='SoftAssertions'].internal.present").shouldHave(text("Soft assertions")).click();
        $x("//h4[contains(text(),'Using JUnit5')]").shouldBe(visible);
        /*
        Ниже я сделал проверку на количество span на случай, если в блоке кода исчезнет какое-то слово или символ
        и проверку по ключевому слову 'SoftAssertsExtension' так как оно есть только в Junit5
        */
        $$x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[1]//span")
                .shouldHave(CollectionCondition.size(25));
        $x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[1]" +
                "//span[contains(text(),'SoftAssertsExtension')]").shouldBe(visible);
        $$x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[2]//span")
                .shouldHave(CollectionCondition.size(28));
        $x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[2]" +
                "//span[contains(text(),'SoftAssertsExtension')]").shouldBe(visible);
    }
}
