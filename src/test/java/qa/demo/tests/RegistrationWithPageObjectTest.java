package qa.demo.tests;


import org.junit.jupiter.api.Test;
import qa.demo.pages.RegistrationPage;

public class RegistrationWithPageObjectTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void testSuccessfulStudentRegistration() {
        openPage("/automation-practice-form");
        registrationPage
                .adBlockAndFooterRemove()
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setUserEmail("iivanov@mail.ru")
                .setGender("Male")
                .setUserNumber("9998887766")
                .setBirthday("15", "December", "1990")
                .setSubject("English")
                .setHobby("Reading")
                .uploadFile("cat.PNG")
                .setCurrentAddress("www.LeningradSPB.ru")
                .setState("Uttar Pradesh")
                .setCity("Agra")
                .submitClick();

        registrationPage
                .assertCheckResultStudentInfo("Ivan Ivanov", "Student Name")
                .assertCheckResultStudentInfo("iivanov@mail.ru", "Student Email")
                .assertCheckResultStudentInfo("Male", "Gender")
                .assertCheckResultStudentInfo("9998887766", "Mobile")
                .assertCheckResultStudentInfo("15 December,1990", "Date of Birth")
                .assertCheckResultStudentInfo("English", "Subjects")
                .assertCheckResultStudentInfo("Reading", "Hobbies")
                .assertCheckResultStudentInfo("cat.PNG", "Picture")
                .assertCheckResultStudentInfo("www.LeningradSPB.ru", "Address")
                .assertCheckResultStudentInfo("Uttar Pradesh Agra", "State and City");
    }
}
