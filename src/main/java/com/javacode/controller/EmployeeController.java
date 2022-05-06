package com.javacode.controller;

import com.javacode.dto.EmployeeDTO;
import com.javacode.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping(value = "/getById", produces = MediaType.TEXT_XML_VALUE)
    public ResponseEntity<?> getStudentById(@RequestBody EmployeeDTO dto) throws JAXBException, IOException {
        return ResponseEntity.ok().body(employeeService.getEmployeeById(dto));

    }

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.addNewEmployee(dto));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.updateEmployeeById(dto));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody EmployeeDTO dto) {
        return ResponseEntity.ok().body(employeeService.deleteEmployeeById(dto));
    }

}
