package qa.demo.datafortests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class RegistrationPageData {

    Random random = new Random();
    Faker fakerRu = new Faker(new Locale("ru")),
            faker = new Faker();
    //int randomNum = random.nextInt(3);
    String[] birthdayDate = getDate(),
            subjectsArray = {"English", "Chemistry", "Computer Science", "Commerce", "Economics",
                    "Social Studies"},
            hobbiesArray = {"Music", "Reading", "Sports"},
            stateArray = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"},
            cityArray1 = {"Delhi", "Gurgaon", "Noida"},
            cityArray2 = {"Agra", "Lucknow", "Merrut"},
            cityArray3 = {"Karnal", "Panipat"},
            cityArray4 = {"Jaipur", "Jaiselmer"};
    int randomIndex = random.nextInt(stateArray.length);
    String[] cityArrayResult = getCity();
    public String firstName = fakerRu.name().firstName(),
            lastName = fakerRu.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            userPhone = "999" + faker.number().numberBetween(1000000, 9999999),
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
    public String[] getDate() {
        SimpleDateFormat formatOfDate = new SimpleDateFormat("M/dd/yyyy");
        String formatedDate = formatOfDate.format(fakerRu.date().birthday());
        String[] monthDayYear = formatedDate.split("/");
        Month month = Month.of(Integer.parseInt(monthDayYear[0]));
        monthDayYear[0] = month.toString().substring(0, 1).toUpperCase()
                + month.toString().substring(1).toLowerCase();
        return monthDayYear;
    }

    public String[] getCity() {
        ArrayList<String[]> listWithCities = new ArrayList<>();
        listWithCities.add(cityArray1);
        listWithCities.add(cityArray2);
        listWithCities.add(cityArray3);
        listWithCities.add(cityArray4);
        return randomIndex == 0 ? listWithCities.get(0) :
                randomIndex == 1 ? listWithCities.get(1) :
                        randomIndex == 2 ? listWithCities.get(2) :
                                listWithCities.get(3);
    }
}
