package com.javacode.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String department;
    private String phone;
    private String address;
}
