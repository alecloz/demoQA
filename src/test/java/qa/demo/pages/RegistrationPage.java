package qa.demo.pages;

import com.codeborne.selenide.SelenideElement;
import qa.demo.pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationPage {

    CalendarComponent calendarComponent = new CalendarComponent();
    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderPick = $("#genterWrapper"),
            userNumberInput = $x("//input[@placeholder = 'Mobile Number']"),
            birthdayInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            uploadButton = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $x("//div[@id='state']//input"),
            cityInput = $x("//div[@id='city']//input"),
            submitButton = $("#submit");

    public String getValueFromResultTable(String value) {
        return String.format("//td[text()='%s']/following-sibling::*", value);
    }

    public RegistrationPage assertCheckResultStudentInfo(String expected, String valueFromResultTable) {
        assertEquals(String.format("%s", expected),
                $x(getValueFromResultTable(String.format("%s", valueFromResultTable))).getText());
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.val(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.val(lastName);
        return this;
    }

    public RegistrationPage setUserEmail(String userEmail) {
        userEmailInput.val(userEmail);
        return this;
    }

    public RegistrationPage setGender(String userGender) {
        userGenderPick.$(byText(userGender)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.val(userNumber);
        return this;
    }

    public RegistrationPage setBirthday(String day, String month, String year) {
        birthdayInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.val(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobby(String hobby) {
        $x("//label[contains(text(), '" + hobby + "')]").click();
        return this;
    }

    public RegistrationPage uploadFile(String classPathFile) {
        uploadButton.uploadFromClasspath(classPathFile);
        return this;
    }

    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressInput.val(currentAddress);
        return this;
    }

    public RegistrationPage setState(String state) {
        stateInput.val(state).pressEnter();
        return this;
    }

    public RegistrationPage setCity(String city) {
        cityInput.val(city).pressEnter();
        return this;
    }

    public void submitClick() {
        submitButton.click();
    }

}
