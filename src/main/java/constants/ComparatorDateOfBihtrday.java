package constants;

import model.Doctor;

import java.util.Comparator;

public class ComparatorDateOfBihtrday implements Comparator<Doctor> {

    @Override
    public int compare(Doctor o1, Doctor o2) {
        return o1.getDateOfBirthday().compareTo(o2.getDateOfBirthday());
    }
}
