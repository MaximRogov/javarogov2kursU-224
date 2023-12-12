package org.example;

public class Abonent {
    private final String name;
    private final String surname;
    private final String phoneNumberOne;
    private final String phoneNumberTwo;
    private final String phoneNumberThree;
    private final int id;

    public Abonent(int id,String name, String surname, String phoneNumberOne, String phoneNumberTwo, String phoneNumberThree) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumberOne = phoneNumberOne;
        this.phoneNumberTwo = phoneNumberTwo;
        this.phoneNumberThree = phoneNumberThree;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumberOne() {
        return phoneNumberOne;
    }

    public String getPhoneNumberTwo() {
        return phoneNumberTwo;
    }

    public String getPhoneNumberThree() {
        return phoneNumberThree;
    }

    @Override
    public String toString() {
        return  "------------------ "+ '\n' +
                "ID: " + id + '\n' +
                "Имя: " + name + '\n' +
                "Фамилия: " + surname + '\'' +
                "Номер телефона 1: " + phoneNumberOne + '\n' +
                "Номер телефона 2: " + phoneNumberTwo + '\n' +
                "Номер телефона 3: " + phoneNumberThree + '\n' +
                "------------------ ";
    }
}
