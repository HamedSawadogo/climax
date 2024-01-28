package com.climax.climax.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class Employee implements Serializable {
    private String id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private int age;
    @NotNull
    private Double salary;
    @NotNull
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
