package com.climax.climax.model;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

@Slf4j
public class CsvFile extends FileFormat{

    @Override
    public Set<Employee> readFile(String filePath) throws IOException {

        try {
            // Create object of filereader
            // class with csv file as parameter.
            FileReader filereader = new FileReader(filePath);

            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            // create csvReader object with
            // parameter filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .build();


            // String csvFile = "employees.csv"; // Chemin vers votre fichier CSV
            // String[] requiredColumns = {"firstName", "lastName", "age"}; // Colonnes requises
    
            // try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            //     String[] headers = reader.readNext(); // Lire la première ligne pour obtenir les en-têtes
    
            //     // Trouver les index des colonnes requises
            //     int[] requiredColumnIndexes = new int[requiredColumns.length];
            //     for (int i = 0; i < requiredColumns.length; i++) {
            //         for (int j = 0; j < headers.length; j++) {
            //             if (headers[j].equalsIgnoreCase(requiredColumns[i])) {
            //                 requiredColumnIndexes[i] = j;
            //                 break;
            //             }
            //         }
            //     }
    
            //     // Lire et afficher les lignes en sélectionnant les colonnes requises
            //     String[] nextLine;
            //     while ((nextLine = reader.readNext()) != null) {
            //         for (int index : requiredColumnIndexes) {
            //             System.out.print(nextLine[index] + " ");
            //         }
            //         System.out.println();
            //     }       
        // Read all data at once
            List<String[]> allData = csvReader.readAll();

            // print Data
            for (String[] row : allData) {
                for (String cell : row) {
                    System.out.print(cell + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
