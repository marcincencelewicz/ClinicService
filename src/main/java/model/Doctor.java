package model;

import Interfaces.IReadFile;
import constants.ComparatorDateOfBihtrday;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

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
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("lista nie moze byc pusta");
        }

        String maxDoctorsSpecialization = list.get(0).getSpeciality();
        List<String> specializations = new ArrayList<>();
        HashSet<String> specjalizacjeBezPowtorzen = new HashSet<>();
        for (Doctor d : list) {
            specializations.add(d.getSpeciality());
            specjalizacjeBezPowtorzen.add(d.getSpeciality());
        }
        int temp1 = 0;
        for (String s1 : specjalizacjeBezPowtorzen) {
            int temp2 = 0;
            for (String s2 : specializations) {
                if (s1.equals(s2)) {
                    temp2++;
                }
                if (temp2 > temp1) {
                    temp1 = temp2;
                    maxDoctorsSpecialization = s1;
                }
            }
        }
        return maxDoctorsSpecialization;
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

    public static void readFile(String path) throws IOException {
        List<String> reading = IReadFile.readFilePath(path);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        for (String s : reading) {
            String[] tab = s.split("\\t");
            Doctor doctor = new Doctor(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], LocalDate.parse(tab[4], dtf), tab[5], tab[6]);
        }
    }

    public static List<Doctor> getExtension() {
        return extension;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getNip() {
        return nip;
    }
}

