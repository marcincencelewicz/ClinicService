import model.Doctor;
import model.Patient;
import model.Visit;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        Doctor.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\doctors.txt");
        Patient.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\patients.txt");
        Visit.readFile("C:\\Users\\CELEK\\IdeaProjects\\ClinicService\\visits.txt");

    }
}
