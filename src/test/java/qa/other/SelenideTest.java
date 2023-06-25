package qa.other;

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
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void checkDisplayingJUnit5BlocksOfCode() {
        open("/selenide/selenide");
        $("#wiki-tab").click();
        $("a[href$='SoftAssertions'].internal.present").shouldHave(text("Soft assertions")).click();
        $x("//button[contains(text(),'Show 2 more pages…')]").click();
        $x("//a[contains(text(),'SoftAssertions')]").shouldBe(visible).click();

        $x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[1]/pre")
                .shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\nclass Tests {\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
        $x("//h4[contains(text(),'Using JUnit5')]/following-sibling::div[2]/pre")
                .shouldHave(text("class Tests {\n" +
                        "  @RegisterExtension \n" +
                        "  static SoftAssertsExtension softAsserts = new SoftAssertsExtension();\n" +
                        "\n" +
                        "  @Test\n" +
                        "  void test() {\n" +
                        "    Configuration.assertionMode = SOFT;\n" +
                        "    open(\"page.html\");\n" +
                        "\n" +
                        "    $(\"#first\").should(visible).click();\n" +
                        "    $(\"#second\").should(visible).click();\n" +
                        "  }\n" +
                        "}"));
    }
}
