package com.climax.climax.dao;

import com.climax.climax.model.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Bean Spring
@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private final List<Employee>employees;

    public EmployeeDaoImpl() {
        this.employees = new ArrayList<>();
    }
    //Ajouter Un fichier d'Employee (composé de 1 ou plusieurs Employées)
    @Override
    public void addEmployees(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    //Renvoie la liste des Employees en Mémoire
    @Override
    public List<Employee>getEmployeesList() {
        return this.employees;
    }
}
