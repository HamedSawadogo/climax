package com.climax.climax.model;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CsvFile extends File{

    @Override
    public List<Employee> readFile(String filePath) throws IOException {
        CSVReader csvReader = null;
        csvReader = new CSVReader(new FileReader(filePath));
        String[] value;
        while ((value = csvReader.readNext()) != null) {
            System.out.println(value[0] + " " + value[1] + " " + value[2]);
        }
        return null;
    }
}
