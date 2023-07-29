package qa.demo.tests.jenkins.properties;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa.demo.datafortests.RegistrationPageData;
import qa.demo.pages.RegistrationPage;
import qa.demo.tests.jenkins.simple.BaseRemoteTest;

import static io.qameta.allure.Allure.step;


public class JavaFakerJenkinsPropertiesTest extends BaseRemoteTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageData registrationPageData = new RegistrationPageData();

    @Tag("remote")
    @DisplayName("Successful registration")
    @Test
    public void testSuccessfulStudentRegistration() {
        step("Open form", () -> {
            openPage("/automation-practice-form");
            registrationPage.adBlockAndFooterRemove();
        });
        step("Fill form", () -> {
            registrationPage.setFirstName(registrationPageData.firstName)
                    .setLastName(registrationPageData.lastName)
                    .setUserEmail(registrationPageData.userEmail)
                    .setGender(registrationPageData.gender)
                    .setUserNumber(registrationPageData.userPhone)
                    .setBirthday(registrationPageData.dayOfBirthday,
                            registrationPageData.monthOfBirthday,
                            registrationPageData.yearOfBirthday)
                    .setSubject(registrationPageData.subject)
                    .setHobby(registrationPageData.hobby)
                    .uploadFile(registrationPageData.uploadFilePath)
                    .setCurrentAddress(registrationPageData.currentAddress)
                    .setState(registrationPageData.state)
                    .setCity(registrationPageData.city)
                    .submitClick();
        });
        step("Verify results", () -> {
            registrationPage
                    .assertCheckResultStudentInfo(registrationPageData.firstName + " "
                            + registrationPageData.lastName, "Student Name")
                    .assertCheckResultStudentInfo(registrationPageData.userEmail, "Student Email")
                    .assertCheckResultStudentInfo(registrationPageData.gender, "Gender")
                    .assertCheckResultStudentInfo(registrationPageData.userPhone, "Mobile")
                    .assertCheckResultStudentInfo(registrationPageData.dayOfBirthday + " "
                            + registrationPageData.monthOfBirthday + ","
                            + registrationPageData.yearOfBirthday, "Date of Birth")
                    .assertCheckResultStudentInfo(registrationPageData.subject, "Subjects")
                    .assertCheckResultStudentInfo(registrationPageData.hobby, "Hobbies")
                    .assertCheckResultStudentInfo(registrationPageData.uploadFilePath, "Picture")
                    .assertCheckResultStudentInfo(registrationPageData.currentAddress, "Address")
                    .assertCheckResultStudentInfo(registrationPageData.state + " "
                            + registrationPageData.city, "State and City");
        });
    }
}
