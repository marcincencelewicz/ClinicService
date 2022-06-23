package model;


import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Visit {
    private static final List<Visit> extension = new ArrayList<>();
    private Doctor doctor;
    private Patient patient;
    private LocalDate dateOfVisit;

    public Visit(int idDoctor, int idPatient, LocalDate dateOfVisit) throws IllegalAccessException {
        this.doctor = findDoctor(idDoctor, Doctor.getExtension());
        this.patient = findPatient(idPatient, Patient.getExtension());
        this.dateOfVisit = dateOfVisit;

        doctor.addVisit(this);
        patient.addVisit(this);
        extension.add(this);
    }

    public static int theMostVisitOfYear(List<Visit> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(v -> v.getDateOfVisit().getYear())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(s -> s.getKey())
                .orElseThrow();
    }

    public Doctor findDoctor(int idDoctor, List<Doctor> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(doctor -> doctor.getId() == idDoctor)
                .findFirst()
                .orElseThrow();
    }

    public Patient findPatient(int idPatient, List<Patient> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(patient -> patient.getId() == idPatient)
                .findFirst()
                .orElseThrow();
    }

    public static final List<Visit> getExtension() {
        return extension;
    }

    public final LocalDate getDateOfVisit() {
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
