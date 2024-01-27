package com.climax.climax.model;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JsonFile extends File{

    @Override
    public List<Employee> readFile(String filePath) throws IOException, ParseException {
        log.info("myFilePath:   "+filePath);
        JSONParser parser=new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));


        JSONArray employeesList=(JSONArray)obj;
        List<Employee>employees=new ArrayList<>();
        //Iterate over employee array
        employeesList.forEach(employeeParam -> {
            JSONObject employeeObject=(JSONObject)employeeParam;
            Employee employee=Employee.builder()
            .age(Integer.parseInt(String.valueOf(employeeObject.get("age"))))
            .firstName(String.valueOf(employeeObject.get("firstName")))
            .lastName(String.valueOf(employeeObject.get("lastName")))
            .djob(String.valueOf(employeeObject.get("djob")))
            .salary(Double.valueOf(String.valueOf(employeeObject.get("salary"))))
            .build();
            //
            employees.add(employee);
        });
        return employees;
    }
}
