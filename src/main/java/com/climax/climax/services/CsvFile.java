package com.climax.climax.services;

import com.climax.climax.exceptions.EmptyFileException;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileFormat;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Slf4j
public class CsvFile implements FileFormat {

    @Override
    public Set<Employee> readFile(String filePath) throws IOException {

        FileReader filereader = new FileReader(filePath);
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        List<String[]> allData = csvReader.readAll();

        Set<Employee> employees = new HashSet<>();
        if (allData.isEmpty()) throw new EmptyFileException("ce fichier csv est vide");
        for (String[] row : allData) {
            employees.add(initEmployee(row));
        }
        return employees;
    }
    private  Employee initEmployee(String[] fileds){
        Employee employee = Employee.builder()
                .firstName(fileds[0])
                .lastName(fileds[1])
                .age(Integer.parseInt(fileds[2]))
                .djob(fileds[3])
                .salary(Double.parseDouble(fileds[4]))
                .build();
        log.info(employee.toString());
        return employee;
    }
}
