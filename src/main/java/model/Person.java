package model;

import java.time.LocalDate;

public abstract class Person {
    private int id;
    private String name;
    private String surname;
    private LocalDate dateOfBirthday;
    private String personalId;

    public  Person(int id, String name, String surname, LocalDate dateOfBirthday, String personalId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
        this.personalId = personalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public String getPersonalId() {
        return personalId;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
