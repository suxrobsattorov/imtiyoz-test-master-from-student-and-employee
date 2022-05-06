package com.javacode.controller;

import com.javacode.dto.ImtiyozTypeDTO;
import com.javacode.service.ImtiyozTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imtiyoztype")
public class ImtiyozTypeController {

    @Autowired
    private ImtiyozTypeService imtiyozTypeService;

    @PostMapping
    public ResponseEntity<ImtiyozTypeDTO> create(@RequestBody ImtiyozTypeDTO dto) {
        ImtiyozTypeDTO response = imtiyozTypeService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(imtiyozTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(imtiyozTypeService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody ImtiyozTypeDTO dto) {
        imtiyozTypeService.update(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        imtiyozTypeService.delete(id);
        return ResponseEntity.ok().build();
    }
}