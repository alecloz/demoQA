package qa.demo.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {

    @BeforeAll
    static void preconditionConfiguration() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void testSuccessfulStudentRegistration() {
        open("/automation-practice-form");
        getWebDriver().manage().window().maximize();
        adBlockAndFooterRemove();
        $("#firstName").val("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail-wrapper #userEmail").setValue("iivanov@mail.ru");
        $x("//label[contains(text(),'Male')]").click();
        $x("//input[@placeholder = 'Mobile Number']").setValue("9998887766");
        $("#dateOfBirthInput").click();
        $x("//select[contains(@class, 'year-select')]").selectOption("1990");
        $x("//select[contains(@class, 'month-select')]").selectOption("December");
        $x("//div[contains(text(), '15')]").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $x("//label[contains(text(),'Reading')]").click();
        $("#uploadPicture").uploadFromClasspath("cat.PNG");
        $("#currentAddress").setValue("www.LeningradSPB.ru");
        $x("//div[@id='state']//input").setValue("Uttar Pradesh").sendKeys(Keys.ENTER);
        $x("//div[@id='city']//input").setValue("Agra").sendKeys(Keys.ENTER);
        $("#submit").click();
        assertCheckResultStudentInfo("Ivan Ivanov", "Student Name");
        assertCheckResultStudentInfo("iivanov@mail.ru", "Student Email");
        assertCheckResultStudentInfo("Male", "Gender");
        assertCheckResultStudentInfo("9998887766", "Mobile");
        assertCheckResultStudentInfo("15 December,1990", "Date of Birth");
        assertCheckResultStudentInfo("English", "Subjects");
        assertCheckResultStudentInfo("Reading", "Hobbies");
        assertCheckResultStudentInfo("cat.PNG", "Picture");
        assertCheckResultStudentInfo("www.LeningradSPB.ru", "Address");
        assertCheckResultStudentInfo("Uttar Pradesh Agra", "State and City");
    }

    public String getValueFromResultTable(String value) {
        return String.format("//td[text()='%s']/following-sibling::*", value);
    }

    public void assertCheckResultStudentInfo(String expected, String valueFromResultTable) {
        assertEquals(String.format("%s", expected),
                $x(getValueFromResultTable(String.format("%s", valueFromResultTable))).getText());
    }

    void adBlockAndFooterRemove() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }
}

