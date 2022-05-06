package com.javacode.controller;

import com.javacode.dto.StudentDTO;
import com.javacode.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken() {
        return ResponseEntity.ok().body(studentService.getToken());
    }

    @PostMapping("/getAll")
    public ResponseEntity<List<?>> getStudents() {
        List<StudentDTO> dtoList = studentService.getAllStudents();
        return dtoList != null ? ResponseEntity.ok(dtoList) : ResponseEntity.badRequest().build();
    }

    @PostMapping("/getById/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Integer id) {
        StudentDTO dto = studentService.getStudentById(id);
        return dto != null ? ResponseEntity.ok().body(dto) : ResponseEntity.badRequest().build();

    }

    @PostMapping("/save")
    public StudentDTO saveStudent(@RequestBody StudentDTO dto) {
        return studentService.addNewStudent(dto);
    }

    @PostMapping("/update/{id}")
    public StudentDTO update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        return studentService.updateStudentById(id, dto);
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        return studentService.deleteStudentById(id);
    }

}
