package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    private static List<Patient> extension = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();

    public Patient(int id, String name, String surname, LocalDate dateOfBirthday, String personalId) {
        super(id, name, surname, dateOfBirthday, personalId);

        extension.add(this);
    }

    public static List<Patient> getExtension() {
        return extension;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
