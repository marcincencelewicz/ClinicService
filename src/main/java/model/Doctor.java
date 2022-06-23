package model;

import constants.ComparatorDateOfBihtrday;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Doctor extends Person {
    private static final List<Doctor> extension = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();
    private String speciality;
    private final String nip;

    public Doctor(int id, String name, String surname, String speciality, LocalDate dateOfBirthday, String personalId, String nip) {
        super(id, name, surname, dateOfBirthday, personalId);
        this.speciality = speciality;
        this.nip = nip;

        extension.add(this);
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public static Doctor theMostVisit(List<Doctor> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparingLong(d -> d.getVisits().size()))
                .orElseThrow();
    }

    public static String theMostSpeciality() {
        return theMostVisit(Doctor.getExtension()).getSpeciality();
    }

    public static String theMostDoctorsAreSpecialization(List<Doctor> list) {
        return numberOfSpeciality(list)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
    }

    public static Map<String, Long> numberOfSpeciality(List<Doctor> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(d -> d.getSpeciality())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static long numberOfDifferentSpecializations(List<Doctor> list) {
        return Optional.ofNullable(list)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(d -> d.getSpeciality())
                .distinct()
                .count();
    }

    public static List<Doctor> nTopMostOldest(List<Doctor> list, int nTop) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list must not empty");
        }
        if (nTop > list.size()) {
            throw new IllegalArgumentException("wrong data");
        }
        List<Doctor> mostOldest = new ArrayList<>(list);
        Collections.sort(mostOldest, new ComparatorDateOfBihtrday());
        return mostOldest.subList(0, nTop);
    }

    public static final List<Doctor> getExtension() {
        return extension;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public final String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public final String getNip() {
        return nip;
    }
}

