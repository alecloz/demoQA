package qa.demo.tests;


import org.junit.jupiter.api.Test;
import qa.demo.pages.RegistrationPage;

import static qa.demo.dataForTests.RegistrationPageData.*;

public class RegistrationWithPageObjectAndFakerTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void testSuccessfulStudentRegistration() {
        openPage("/automation-practice-form");
        registrationPage
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(userEmail)
                .setGender(gender)
                .setUserNumber(userPhone)
                .setBirthday(dayOfBirthday, monthOfBirthday, yearOfBirthday)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(uploadFilePath)
                .setCurrentAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitClick();

        registrationPage
                .assertCheckResultStudentInfo(firstName + " " + lastName, "Student Name")
                .assertCheckResultStudentInfo(userEmail, "Student Email")
                .assertCheckResultStudentInfo(gender, "Gender")
                .assertCheckResultStudentInfo(userPhone, "Mobile")
                .assertCheckResultStudentInfo(dayOfBirthday + " " + monthOfBirthday + "," + yearOfBirthday, "Date of Birth")
                .assertCheckResultStudentInfo(subject, "Subjects")
                .assertCheckResultStudentInfo(hobby, "Hobbies")
                .assertCheckResultStudentInfo(uploadFilePath, "Picture")
                .assertCheckResultStudentInfo(currentAddress, "Address")
                .assertCheckResultStudentInfo(state + " " + city, "State and City");
    }
}
