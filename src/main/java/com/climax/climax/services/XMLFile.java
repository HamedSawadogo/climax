package com.climax.climax.services;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileFormat;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.services.FileManager;

import javax.xml.parsers.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class XMLFile implements FileFormat {

    @Override
    public Set<Employee> readFile(String filePath) throws IOException,
            ParserConfigurationException, SAXException {

        if (!FileManager.isValidFilePath(filePath)) {
            throw new FileNotFoundException("ce fichier envoyé est invalide!!!");
        }
        log.info("myFilePath:   " + filePath);
        DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dBfactory.newDocumentBuilder();

        Document document = builder.parse(new java.io.File(filePath));
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("employee");


        Set<Employee> employeeList = new HashSet<>();
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Employee employee = Employee.builder()
                        .id(element.getAttribute("id"))
                        .firstName(element.getElementsByTagName("firstName").item(0).getTextContent())
                        .lastName(element.getElementsByTagName("lastName").item(0).getTextContent())
                        .age(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()))
                        .djob(element.getElementsByTagName("djob").item(0).getTextContent())
                        .salary(Double.valueOf(element.getElementsByTagName("salary").item(0).getTextContent()))
                        .build();
                log.info(employee.toString());
                employeeList.add(employee);
            } else {
                throw new FileNotFoundException("une Erreur est survenue lors de la lecture de fichier");
            }
        }
        return employeeList;
    }
}
