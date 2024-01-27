package com.climax.climax;

import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.CsvFile;
import com.climax.climax.model.Employee;
import com.climax.climax.model.File;
import com.climax.climax.model.JsonFile;
import com.climax.climax.services.FileExtensionsManager;
import com.climax.climax.services.FileFactory;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@SpringBootApplication
public class ClimaxApplication { public static void main(String[] args) {
        SpringApplication.run(ClimaxApplication.class, args);
    }

    CommandLineRunner commandLineRunner(){
      return args -> {
          ClimaxServiceImpl climaxService=new ClimaxServiceImpl();


          String filePath="C:\\Users\\Hamed\\IdeaProjects\\climax\\file.txt";
          String jsonFile="C:\\Users\\Hamed\\IdeaProjects\\climax\\file.json";
          String xmlFile="C:\\Users\\Hamed\\IdeaProjects\\climax\\file.xml";



          climaxService.setFile(FileFactory.createFileReader(xmlFile));
          List<Employee>employees=climaxService.readFile(xmlFile);
          employees.forEach(employee -> {
              System.err.println(employee.getFirstName());
          });


      };
    }

}
