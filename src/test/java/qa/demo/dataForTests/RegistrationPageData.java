package qa.demo.dataForTests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class RegistrationPageData {

    static Random random = new Random();
    static Faker fakerRu = new Faker(new Locale("ru"));
    static Faker faker = new Faker();
    static int randomNum = random.nextInt(3);
    static String[] birthdayDate = getDate();
    static String[] subjectsArray = {"English", "Chemistry", "Computer Science", "Commerce", "Economics",
            "Social Studies"};
    static String[] hobbiesArray = {"Music", "Reading", "Sports"};
    static String[] stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    static String[] cityArray1 = {"Delhi", "Gurgaon", "Noida"};
    static String[] cityArray2 = {"Agra", "Lucknow", "Merrut"};
    static String[] cityArray3 = {"Karnal", "Panipat"};
    static String[] cityArray4 = {"Jaipur", "Jaiselmer"};
    static int randomIndex = random.nextInt(stateArray.length);
    static ArrayList<String[]> listWithCities = new ArrayList<>();

    static {
        listWithCities.add(cityArray1);
        listWithCities.add(cityArray2);
        listWithCities.add(cityArray3);
        listWithCities.add(cityArray4);
    }

    static String[] cityArrayResult = randomIndex == 0 ? listWithCities.get(0) :
            randomIndex == 1 ? listWithCities.get(1) :
                    randomIndex == 2 ? listWithCities.get(2) :
                            listWithCities.get(3);

    public static String firstName = fakerRu.name().firstName(),
            lastName = fakerRu.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = randomNum == 2 ? "Male" : randomNum == 1 ? "Female" : "Other",
            userPhone = "999" + (random.nextInt(9000000) + 1000000),
            monthOfBirthday = birthdayDate[0],
            dayOfBirthday = birthdayDate[1],
            yearOfBirthday = birthdayDate[2],
            subject = subjectsArray[random.nextInt(subjectsArray.length)],
            hobby = hobbiesArray[random.nextInt(hobbiesArray.length)],
            uploadFilePath = "cat.PNG",
            currentAddress = fakerRu.address().fullAddress(),
            state = stateArray[randomIndex],
            city = cityArrayResult[random.nextInt(cityArrayResult.length)];

    /**
     * Return String array with 3 elements of birthday date, where:
     * index 0 - month;
     * index 1 - day;
     * index 2 - year;
     */
    public static String[] getDate() {
        SimpleDateFormat formatOfDate = new SimpleDateFormat("M/dd/yyyy");
        String formatedDate = formatOfDate.format(fakerRu.date().birthday());
        String[] monthDayYear = formatedDate.split("/");
        Month month = Month.of(Integer.parseInt(monthDayYear[0]));
        monthDayYear[0] = month.toString().substring(0, 1).toUpperCase()
                + month.toString().substring(1).toLowerCase();
        return monthDayYear;
    }
}
