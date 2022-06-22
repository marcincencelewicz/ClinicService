package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Visit {
    private static List<Visit> extension = new ArrayList<>();
    private Doctor doctor;
    private Patient patient;
    private LocalDate dateOfVisit;

    public static void readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<String> reading = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            reading.add(line);
        }
        reading.remove(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        for (String s : reading) {
            String[] tab = s.split("\\t");
            Visit visit = new Visit(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), LocalDate.parse(tab[2], dtf));
        }
    }

    public Visit(int idDoctor, int idPatient, LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public static List<Visit> getExtension() {
        return extension;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return dateOfVisit + "";
    }
}
