package com.climax.climax.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class Employee implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Double salary;
    private String djob;

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", djob='" + djob + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return age == employee.age && Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(salary, employee.salary) && Objects.equals(djob, employee.djob);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, age, salary, djob);
    }
}
