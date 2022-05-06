package com.javacode.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement
public class EmployeeXML {

    private Long id;
    private String name;
    private String departament;
    private String phone;
    private String address;
}
