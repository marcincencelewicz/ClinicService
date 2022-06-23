package model;


import java.time.LocalDate;
import java.util.*;

public class Patient extends Person {
    private static final List<Patient> extension = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();

    public Patient(int id, String name, String surname, String personalId, LocalDate dateOfBirthday) {
        super(id, name, surname, dateOfBirthday, personalId);

        extension.add(this);
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public static Patient theMostVisit(List<Patient> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingLong(d -> d.getVisits().size()))
                .orElseThrow();
    }

    public static final List<Patient> getExtension() {
        return extension;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
