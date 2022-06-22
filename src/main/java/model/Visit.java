package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Visit {
    private static List<Visit> extension = new ArrayList<>();
    private Doctor doctor;
    private Patient patient;
    private LocalDate dateOfVisit;

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
