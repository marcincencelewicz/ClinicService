package model;

import java.time.LocalDate;

public abstract class Person {
    private int id;
    private final String name;
    private final String surname;
    private final LocalDate dateOfBirthday;
    private final String personalId;

    public Person(int id, String name, String surname, LocalDate dateOfBirthday, String personalId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
        this.personalId = personalId;
    }

    public final int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public final String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
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
