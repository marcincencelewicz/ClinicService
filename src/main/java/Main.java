import model.Doctor;
import model.Patient;
import model.Visit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {


        readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\doctors.txt", Doctor.class);
        readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\patients.txt", Patient.class);
        readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\visits.txt", Visit.class);

        System.out.println(Doctor.theMostVisit(Doctor.getExtension()));
        System.out.println(Patient.theMostVisit(Patient.getExtension()));
        System.out.println(Doctor.theMostSpeciality());
        System.out.println(Visit.theMostVisitOfYear(Visit.getExtension()));
        System.out.println(Doctor.theMostDoctorsAreSpecialization(Doctor.getExtension()));
        System.out.println(Doctor.numberOfDifferentSpecializations(Doctor.getExtension()));
        System.out.println(Doctor.nTopMostOldest(Doctor.getExtension(), 5));
    }

    public static void readFile(String path, Class clas) throws IOException, IllegalAccessException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<String> reading = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            reading.add(line);
        }
        reading.remove(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        if (clas == Patient.class) {
            for (String s : reading) {
                String[] tab = s.split("\\t");
                Patient patient = new Patient(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], LocalDate.parse(tab[4], dtf));
            }

        } else if (clas == Doctor.class) {
            for (String s : reading) {
                String[] tab = s.split("\\t");
                Doctor doctor = new Doctor(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], LocalDate.parse(tab[4], dtf), tab[5], tab[6]);
            }
        } else if (clas == Visit.class) {
            for (String s : reading) {
                String[] tab = s.split("\\t");
                Visit visit = new Visit(Integer.parseInt(tab[0]), Integer.parseInt(tab[1]), LocalDate.parse(tab[2], dtf));
            }
        } else {
            throw new IllegalArgumentException("wrong path");
        }
    }
}
