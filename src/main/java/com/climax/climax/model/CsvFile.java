package com.climax.climax.model;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvFile extends File{

    @Override
    public List<Employee> readFile(String filePath) throws FileNotFoundException {
        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader(filePath));
            String[] value;
            while ((value = csvReader.readNext()) != null) {
                System.out.println(value[0] + " " + value[1] + " " + value[2]);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }
}
