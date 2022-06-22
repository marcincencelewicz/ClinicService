package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Patient extends Person{
    private static List<Patient> extension = new ArrayList<>();
    private List<Visit> visits = new ArrayList<>();

    public Patient(int id, String name, String surname, String personalId, LocalDate dateOfBirthday) {
        super(id, name, surname, dateOfBirthday, personalId);

        extension.add(this);
    }

    public static void readFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<String> reading = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            reading.add(line);
        }
        reading.remove(0);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
        for (String s : reading) {
            String[] tab = s.split("\\t");
            Patient patient = new Patient(Integer.parseInt(tab[0]), tab[1], tab[2], tab[3], LocalDate.parse(tab[4], dtf));
        }
    }

    public static List<Patient> getExtension() {
        return extension;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
