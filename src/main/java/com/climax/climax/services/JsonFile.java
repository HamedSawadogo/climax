package com.climax.climax.services;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileFormat;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.services.FileManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class JsonFile implements FileFormat {
    /**
     * Lire un fichier Json et renvoie la liste des Utilisateurs associés
     */
    @Override
    public Set<Employee> readFile(String filePath) throws IOException, ParseException {

        if (!FileManager.isValidFilePath(filePath))
            throw new FileNotFoundException("ce fichier envoyé n'est pas valide");
        log.info("myFilePath:   " + filePath);
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));


        JSONArray employeesList = (JSONArray) obj;

        Set<Employee> employees = new HashSet<>();
        //Iterate over employee array
        employeesList.forEach(employeeParam -> {
            JSONObject employeeObject = (JSONObject) employeeParam;
            Employee employee = Employee.builder()
                    .age(Integer.parseInt(String.valueOf(employeeObject.get("age"))))
                    .firstName(String.valueOf(employeeObject.get("firstName")))
                    .lastName(String.valueOf(employeeObject.get("lastName")))
                    .djob(String.valueOf(employeeObject.get("djob")))
                    .salary(Double.valueOf(String.valueOf(employeeObject.get("salary"))))
                    .build();
            log.info(employee.toString());
            //
            employees.add(employee);
        });
        return employees;
    }
}
