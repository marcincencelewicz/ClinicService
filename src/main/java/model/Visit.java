package model;

import Interfaces.IReadFile;

import javax.print.Doc;
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

    public Visit(int idDoctor, int idPatient, LocalDate dateOfVisit) throws IllegalAccessException {
        this.doctor = findDoctor(idDoctor,Doctor.getExtension());
        this.patient = findPatient(idPatient, Patient.getExtension());
        this.dateOfVisit = dateOfVisit;

        doctor.addVisit(this);
        patient.addVisit(this);
        extension.add(this);
    }

    public Doctor findDoctor(int idDoctor, List<Doctor> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list must not empty");
        }
        Doctor foundDoctor = list.get(0);
        for (Doctor doctor : list) {
            if (doctor.getId() == idDoctor) {
                foundDoctor = doctor;
            }
        }
        return foundDoctor;
    }
    public Patient findPatient(int idPatient, List<Patient> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list must not empty");
        }
        Patient foundPatient = list.get(0);
        for (Patient patient : list) {
            if (patient.getId() == idPatient) {
                foundPatient = patient;
            }
        }
        return foundPatient;
    }

    public static void readFile(String path) throws IOException, IllegalAccessException {

        List<String> reading = IReadFile.readFilePath(path);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        for (String s : reading) {
            String[] tab = s.split("\\t");
            Visit visit = new Visit(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), LocalDate.parse(tab[2], dtf));
        }
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
