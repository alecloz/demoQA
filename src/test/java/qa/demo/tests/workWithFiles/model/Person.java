package qa.demo.tests.workWithFiles.model;

import java.util.*;

public class Person {
    public String date;
    public String firstname;
    public String lastname;
    public String city;
    public String country;
    public String countryCode;
    public String email;
    public ArrayList<String> hobby;
    public ArrayList<Friend> friend;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getHobby() {
        return hobby;
    }

    public void setHobby(ArrayList<String> hobby) {
        this.hobby = hobby;
    }

    public ArrayList<Friend> getFriend() {
        return friend;
    }

    public void setFriend(ArrayList<Friend> friend) {
        this.friend = friend;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}