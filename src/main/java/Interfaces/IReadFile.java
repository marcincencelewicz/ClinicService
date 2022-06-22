package Interfaces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IReadFile {
    static List<String> readFilePath(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        List<String> reading = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            reading.add(line);
        }
        reading.remove(0);
        return reading;
    }
}
