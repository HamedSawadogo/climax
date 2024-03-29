package com.climax.climax.dao;

import com.climax.climax.model.Employee;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Component
public class EmployeeDaoImpl implements EmployeeDao {
    private  Set<Employee>employees;
    public EmployeeDaoImpl() {
        this.employees = new HashSet<>();
    }
    /**
     * Ajouter Un fichier d'Employee (composé de 1 ou plusieurs Employées)
     * @param employees
     */
    @Override
    public void addEmployees(Set<Employee> employees) {
        if(employees.isEmpty())throw new NoSuchElementException("la liste des Employees est vide");
        this.employees.addAll(employees);
    }
    /**
     * Renvoie la liste des Employees en Mémoire
     * @return
     */
    @Override
    public Set<Employee>getEmployeesList() {
        return this.employees;
    }
    /**
     * Calculer le salaire des Employees par par profession 
     */
    @Override
    public Map<String, Double> calculateSalaryByDjob() {
  
        if(this.employees.isEmpty())throw new NoSuchElementException("la liste de Employees est vide");
                //Renvoie la liste des Employees Par profession 
      Map<String,List<Employee>>employeesDjobs=
              this.employees.stream().collect(Collectors.groupingBy(Employee::getDjob));

      //clé profession,valeur Salaire
      Map<String,Double>meanByDjpob=new HashMap<>();
      for (Map.Entry<String,List<Employee>>employeesByDjob:employeesDjobs.entrySet()){
         String djob=employeesByDjob.getKey();
         List<Employee>employeeList=employeesByDjob.getValue();

         double totalSalary=employeeList
                 .stream()
                 .mapToDouble(Employee::getSalary)
                 .sum();
         Double meanSalary=totalSalary/employeeList.size();
         meanByDjpob.put(djob,meanSalary);
      }
      return  meanByDjpob;
    }
}
