package com.climax.climax.model;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class TextFile extends FileFormat{
    @Override
    public Set<Employee> readFile(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(filePath);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        // Lecture de tous les objets Employee jusqu'à la fin du fichier
        Set<Employee>employees=new HashSet<>();
        while (true) {
            try {
                Employee employee = (Employee) objectInputStream.readObject();
                employees.add(employee);
                log.info(employee.toString());
            } catch (EOFException e) {
                break;
            }
        }
        return employees;
    }
}
