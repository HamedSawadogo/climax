package com.climax.climax.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Data
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Double salary;
    private String djob;
}
