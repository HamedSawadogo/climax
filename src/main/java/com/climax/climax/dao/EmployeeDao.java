package com.climax.climax.dao;
import com.climax.climax.model.Employee;
import java.util.Map;
import java.util.Set;

public interface EmployeeDao {
    //Permettre d'ajouter les Employees chargées depuis un fichier dans Une Liste d'Employee
    void addEmployees(Set<Employee> employees);

    //Renvoie la liste des Employées chargés depuis les fichiers
    Set<Employee>getEmployeesList();

    //Renvoie la liste des Salaires par Profession
    Map<String,Double> calculateSalaryByDjob();
}
