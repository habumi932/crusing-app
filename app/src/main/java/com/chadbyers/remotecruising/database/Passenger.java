package com.chadbyers.remotecruising.database;

import com.google.gson.annotations.SerializedName;

public class Passenger
{
    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("passport_number")
    private int passportNumber;

    @SerializedName("birthdate")
    private String birthdate;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    public Passenger(String firstName, String lastName, int passportNumber, String birthdate, String city, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.birthdate = birthdate;
        this.city = city;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Full name: " + firstName + " " + lastName + "\n" +
                "Passport Number: " + passportNumber + "\n" +
                "Birthdate: " + birthdate + "\n" +
                "Location: " + city + ", " + state;
    }
}
