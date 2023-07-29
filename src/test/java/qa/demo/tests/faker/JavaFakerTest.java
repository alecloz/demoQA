package qa.demo.tests.faker;


import org.junit.jupiter.api.Test;
import qa.demo.datafortests.RegistrationPageData;
import qa.demo.pages.RegistrationPage;
import qa.demo.tests.pageobject.BaseTest;

public class JavaFakerTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageData registrationPageData = new RegistrationPageData();

    @Test
    public void testSuccessfulStudentRegistration() {
        openPage("/automation-practice-form");
        registrationPage
                .adBlockAndFooterRemove()
                .setFirstName(registrationPageData.firstName)
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
    }
}
