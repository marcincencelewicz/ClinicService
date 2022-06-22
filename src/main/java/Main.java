import model.Doctor;
import model.Patient;
import model.Visit;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException {

        Doctor.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\doctors.txt");
        Patient.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\patients.txt");
        Visit.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\visits.txt");

        System.out.println(Doctor.theMostVisit(Doctor.getExtension()));
        System.out.println(Patient.theMostVisit(Patient.getExtension()));
        System.out.println(Doctor.theMostSpeciality());
        System.out.println(Visit.theMostVisitOfYear(Visit.getExtension()));
        System.out.println(Doctor.theMostDoctorsAreSpecialization(Doctor.getExtension()));
        System.out.println(Doctor.numberOfDifferentSpecializations(Doctor.getExtension()));
        System.out.println(Doctor.nTopMostOldest(Doctor.getExtension(), 5));
    }
}
