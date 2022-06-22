package model;

import Interfaces.IReadFile;
import constants.ComparatorDateOfBihtrday;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Doctor extends Person {
    private static List<Doctor> extension = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();
    private String speciality;
    private String nip;

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
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List must not empty");
        }
        Doctor maxVisit = list.get(0);
        for (Doctor doctor : list) {
            if (maxVisit.getVisits().size() < doctor.getVisits().size()) {
                maxVisit = doctor;
            }
        }
        return maxVisit;
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

    public static int numberOfDifferentSpecializations(List<Doctor> list) {
        HashSet<String> specializations = new HashSet<>();
        for (Doctor doctor : list) {
            specializations.add(doctor.getSpeciality());
        }
        return specializations.size();
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

