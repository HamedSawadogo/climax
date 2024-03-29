package com.climax.climax;
import com.climax.climax.dao.EmployeeDaoImpl;
import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.*;
import com.climax.climax.services.CsvFile;
import com.climax.climax.services.JsonFile;
import com.climax.climax.services.TextFile;
import com.climax.climax.services.XMLFile;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ClimaxApplicationTests {

    private ClimaxServiceImpl climaxService;
    private static final String BASE_URL=System.getProperty("user.dir");
    private Set<Employee> employeesSetTest;

    @BeforeEach
    public void initEmployeesList(){
        Employee employee1 = Employee.builder().age(25).lastName("Adrien").firstName("Zous").djob("informaticien").salary(35d).build();
        Employee employee2 = Employee.builder().age(32).lastName("Mathilde").firstName("Ducroc").djob("informaticien").salary(38d).build();
        Employee employee3 = Employee.builder().age(29).lastName("Bruno").firstName("Joy").djob("comptable").salary(33d).build();
        Employee employee4 = Employee.builder().age(43).lastName("Julien").firstName("Basr").djob("policier").salary(24d).build();
        Employee employee5 = Employee.builder().age(52).lastName("Teff").firstName("Bouaz").djob("boulanger").salary(45d).build();
        Employee employee6 = Employee.builder().age(48).lastName("Ben").firstName("Leroy").djob("comptable").salary(26d).build();
        Employee employee7 = Employee.builder().age(28).lastName("Celine").firstName("Beroy").djob("comptable").salary(37d).build();
        this.employeesSetTest = Set.of(employee1, employee2, employee3, employee4, employee5, employee6, employee7);
    }
    @BeforeEach
    public void initClimax(){
        this.climaxService= new ClimaxServiceImpl();
    }
    private Set<Employee>employees;

    @BeforeEach
    public  void initEmployees(){
        this.employees=new HashSet<>();
    }
    @Test
    @DisplayName("test de lecture des fichiers type xml")
    public void testReadXmlFile(){
        try {
            String filePath=BASE_URL+"/file.xml";
            this.climaxService.setFileReaderFormat(new XMLFile());
            this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ParserConfigurationException | SAXException | ClassNotFoundException e) {
            fail();
        }
    }
    @Test
    @DisplayName("test de lecture des fichiers type csv")
    public void testReadCsvFile() {
        try {
            String filePath = BASE_URL + "/file.csv";
            this.climaxService.setFileReaderFormat(new CsvFile());
            this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ParserConfigurationException | SAXException |
                 ClassNotFoundException e) {
            fail();
        }
    }
    @Test
    @DisplayName("test de lecture des fichiers type txt")
    public void testReadTextFile() {
        try {
            String filePath = BASE_URL + "/file.txt";
            this.climaxService.setFileReaderFormat(new TextFile());
            this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ParserConfigurationException | SAXException |
                 ClassNotFoundException e) {
            fail();
        }
    }
    @Test
    @DisplayName("test de lecture des fichiers json")
    public  void testReadJsonFile(){
        try {
            this.climaxService.setFileReaderFormat(new JsonFile());
            String filePath = BASE_URL + "/file.json";
            this.employees = this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ClassNotFoundException | ParserConfigurationException |
                 SAXException e) {
            log.error("Erreur:  " + e.getMessage());
            fail();
        }
    }
    @Test
    public  void testJSonFileLength(){
        testReadJsonFile();
        Set<Employee> employee=this.employees;
        final int jsonFileLenght=7;
        assertEquals(employee.size(),jsonFileLenght);
    }
    @Test
    @DisplayName("tester la moyenne salariale par Employee")
    public void testGetJSonFileSalaryByDjob(){
        //ARRANGE
        EmployeeDaoImpl employeeDao=new EmployeeDaoImpl();
        employeeDao.setEmployees(this.employeesSetTest);

        Map<String,Double> employeesGroupBySalary=employeeDao.calculateSalaryByDjob();
        Map<String,Double>meanByDjob=new HashMap<>();
        meanByDjob.put("boulanger",45.);
        meanByDjob.put("comptable",32.);
        meanByDjob.put("informaticien",36.5);
        meanByDjob.put("policier",24.);
        boolean mapsAreEqual = true;
        // Vérifier si les deux maps ont les mêmes clés et les mêmes valeurs
        for (Map.Entry<String, Double> entry : meanByDjob.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();

            if (!employeesGroupBySalary.containsKey(key) || !employeesGroupBySalary.get(key).equals(value)) {
                mapsAreEqual = false;
                break;
            }
        }
        assertTrue(mapsAreEqual);

    }
    @DisplayName("tester que toute les données sont correctes")
    @Test
    public void testAllJSonFileData() {
        //Lire le fichier JSON
        testReadJsonFile();
        Set<Employee> employee=this.employees;
        assertEquals(employee,this.employeesSetTest);
    }
    @Test
    @DisplayName("tester les informations sur un Employee")
    public void testGetFirstEmployee(){
        //Lire le fichier JSON
        testReadJsonFile();
        Optional<Employee> employee=this.employees.stream().findFirst();
        Employee employeeTest= Employee.builder()
                .age(25)
                .firstName("Zous")
                .lastName("Adrien")
                .djob("informaticien")
                .salary(35d)
                .build();
        assertEquals(employeeTest,employee.get());
    }


}
