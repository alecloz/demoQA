package qa.demo.tests;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import qa.demo.dataForTests.Gender;
import qa.demo.pages.RegistrationPage;

import java.util.List;
import java.util.stream.Stream;

public class RegistrationWithPageObjectAndParametrizationTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();

    /**
     * Тест с использованием явной параметризации с измененным разделителем
     */
    @DisplayName("Testing registration for 2 different boys")
    @ParameterizedTest(name = "Testing registration for a {3} student {0} {1}")
    @Tags({
            @Tag("smoke"),
            @Tag("regress")
    })
    @CsvSource(value = {
            "Ivan | Ivanov | iivanov@mail.ru | Male | 9998887766 | 15 | December | 1990 | English | Reading | cat.PNG|" +
                    "www.LeningradSPB.ru | Uttar Pradesh | Agra",
            "Egor | Egorov | eegorov@gmail.com | Male | 9991112233 | 01 | June | 1972 | English | Sports | cat.PNG | " +
                    "www.LeningradSPB.ru | NCR | Gurgaon",
    }, delimiter = '|')
    void testSuccessfulStudentRegistrationMan(String first_name, String last_name, String email, String gender,
                                              String phone, String day, String month, String year, String subject,
                                              String hobby, String file, String address, String state, String city) {
        System.out.println("Man");
        openPage("/automation-practice-form");
        registrationPage
                .setFirstName(first_name)
                .setLastName(last_name)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setBirthday(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(file)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submitClick();

        registrationPage
                .assertCheckResultStudentInfo(first_name + " " + last_name, "Student Name")
                .assertCheckResultStudentInfo(email, "Student Email")
                .assertCheckResultStudentInfo(gender, "Gender")
                .assertCheckResultStudentInfo(phone, "Mobile")
                .assertCheckResultStudentInfo(day + " " + month + "," + year,
                        "Date of Birth")
                .assertCheckResultStudentInfo(subject, "Subjects")
                .assertCheckResultStudentInfo(hobby, "Hobbies")
                .assertCheckResultStudentInfo(file, "Picture")
                .assertCheckResultStudentInfo(address, "Address")
                .assertCheckResultStudentInfo(state + " " + city, "State and City");
    }

    /**
     * Тест с использованием параметризации через файл parametrization.csv в папке resources с измененным разделителем
     */
    @DisplayName("Testing registration for 2 different girls")
    @ParameterizedTest(name = "Testing registration for a {3} student {0} {1}")
    @Tags({
            @Tag("smoke"),
            @Tag("regress")
    })
    @CsvFileSource(resources = "/parametrization.csv", delimiter = '|')
    void testSuccessfulStudentRegistrationWoman(String first_name, String last_name, String email, String gender,
                                                String phone, String day, String month, String year, String subject,
                                                String hobby, String file, String address, String state, String city) {
        System.out.println("Woman");
        openPage("/automation-practice-form");
        registrationPage
                .setFirstName(first_name)
                .setLastName(last_name)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(phone)
                .setBirthday(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(file)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submitClick();

        registrationPage
                .assertCheckResultStudentInfo(first_name + " " + last_name, "Student Name")
                .assertCheckResultStudentInfo(email, "Student Email")
                .assertCheckResultStudentInfo(gender, "Gender")
                .assertCheckResultStudentInfo(phone, "Mobile")
                .assertCheckResultStudentInfo(day + " " + month + "," + year,
                        "Date of Birth")
                .assertCheckResultStudentInfo(subject, "Subjects")
                .assertCheckResultStudentInfo(hobby, "Hobbies")
                .assertCheckResultStudentInfo(file, "Picture")
                .assertCheckResultStudentInfo(address, "Address")
                .assertCheckResultStudentInfo(state + " " + city, "State and City");
    }

    /**
     * Тест с использованием параметризации через получение данных из статического метода
     * (имя статик метода должно быть равно названию теста)
     */
    static Stream<Arguments> testSuccessfulStudentRegistrationForAllGendersWithData() {
        return Stream.of(
                Arguments.of(Gender.MALE, List.of("Ivan", "Ivanov", "iivanov@mail.ru", "Male", "9998887766",
                        "15", "December", "1990", "English", "Reading", "cat.PNG", "www.LeningradSPB.ru",
                        "Uttar Pradesh", "Agra")),
                Arguments.of(Gender.FEMALE, List.of("Nika", "Aleksandrova", "naleksandrova@gmail.com",
                        "Female", "9991112233", "25", "September", "2000", "English", "Sports", "cat.PNG",
                        "www.LeningradSPB.ru", "NCR", "Gurgaon")),
                Arguments.of(Gender.OTHER, List.of("pokemon", "pikachu", "pika4u@gmail.com", "Other",
                        "9999999999", "01", "September", "2000", "English", "Sports", "cat.PNG", "www.LeningradSPB.ru",
                        "NCR", "Gurgaon"))
        );
    }

    @MethodSource
    @DisplayName("Testing registration for 3 genders")
    @ParameterizedTest
    @Tag("parametrization")
    void testSuccessfulStudentRegistrationForAllGendersWithData(Gender gender, List<String> person) {
        openPage("/automation-practice-form");
        registrationPage
                .setFirstName(person.get(0))
                .setLastName(person.get(1))
                .setUserEmail(person.get(2))
                .setGender(person.get(3))
                .setUserNumber(person.get(4))
                .setBirthday(person.get(5), person.get(6), person.get(7))
                .setSubject(person.get(8))
                .setHobby(person.get(9))
                .uploadFile(person.get(10))
                .setCurrentAddress(person.get(11))
                .setState(person.get(12))
                .setCity(person.get(13))
                .submitClick();

        registrationPage
                .assertCheckResultStudentInfo(person.get(0) + " " + person.get(1),
                        "Student Name")
                .assertCheckResultStudentInfo(person.get(2), "Student Email")
                .assertCheckResultStudentInfo(person.get(3), "Gender")
                .assertCheckResultStudentInfo(person.get(4), "Mobile")
                .assertCheckResultStudentInfo(person.get(5) + " " + person.get(6) + "," + person.get(7),
                        "Date of Birth")
                .assertCheckResultStudentInfo(person.get(8), "Subjects")
                .assertCheckResultStudentInfo(person.get(9), "Hobbies")
                .assertCheckResultStudentInfo(person.get(10), "Picture")
                .assertCheckResultStudentInfo(person.get(11), "Address")
                .assertCheckResultStudentInfo(person.get(12) + " " + person.get(13),
                        "State and City");
    }

    /**
     * Тест с использованием параметризации для одного аргумента
     */
    @DisplayName("Testing registration for all type of genders")
    @ParameterizedTest
    @Tag("regress")
    @ValueSource(strings = {"Male", "Female", "Other"})
    void testSuccessfulStudentRegistrationOther(String gender) {
        System.out.println("Other");
        openPage("/automation-practice-form");
        registrationPage
                .setFirstName("Ivan")
                .setLastName("Ivanov")
                .setUserEmail("iivanov@mail.ru")
                .setGender(gender)
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
                .assertCheckResultStudentInfo(gender, "Gender")
                .assertCheckResultStudentInfo("9998887766", "Mobile")
                .assertCheckResultStudentInfo("15 December,1990", "Date of Birth")
                .assertCheckResultStudentInfo("English", "Subjects")
                .assertCheckResultStudentInfo("Reading", "Hobbies")
                .assertCheckResultStudentInfo("cat.PNG", "Picture")
                .assertCheckResultStudentInfo("www.LeningradSPB.ru", "Address")
                .assertCheckResultStudentInfo("Uttar Pradesh Agra", "State and City");
    }

    /**
     * Отключенный тест
     */
    @Test
    @DisplayName("Отключенный тест")
    @Disabled
    @Tag("disabled")
    void testSuccessfulStudentRegistrationDisabled() {
        System.out.println("Disabled");
        openPage("/automation-practice-form");
        registrationPage
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
