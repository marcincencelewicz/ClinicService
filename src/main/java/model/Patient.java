package model;

import Interfaces.IReadFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not empty");
        }
        Patient maxVisit = list.get(0);
        for (Patient patient : list) {
            if (maxVisit.getVisits().size() < patient.getVisits().size()) {
                maxVisit = patient;
            }
        }
        return maxVisit;
    }

    public static void readFile(String path) throws IOException {
        List<String> reading = IReadFile.readFilePath(path);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        for (String s : reading) {
            String[] tab = s.split("\\t");
            Patient patient = new Patient(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], LocalDate.parse(tab[4], dtf));
        }
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
