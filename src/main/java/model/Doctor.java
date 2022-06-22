package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends Person {
   private static List<Doctor> extension = new ArrayList<>();
   private List<Visit> visits = new ArrayList<>();
   private String speciality;
   private String nip;

   public Doctor(int id, String name, String surname, LocalDate dateOfBirthday, String personalId, String speciality, String nip) {
      super(id, name, surname, dateOfBirthday, personalId);
      this.speciality = speciality;
      this.nip = nip;

      extension.add(this);
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

