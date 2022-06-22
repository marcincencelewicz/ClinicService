package model;

import Interfaces.IReadFile;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

