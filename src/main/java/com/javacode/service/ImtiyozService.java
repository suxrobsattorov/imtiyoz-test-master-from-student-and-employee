package com.javacode.service;

import com.javacode.dto.ImtiyozDTO;
import com.javacode.dto.ImtiyozStudentDTO;
import com.javacode.dto.StudentDTO;
import com.javacode.entity.ImtiyozEntity;
import com.javacode.entity.ImtiyozTypeEntity;
import com.javacode.repository.ImtiyozRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class ImtiyozService {
    @Autowired
    private ImtiyozRepository imtiyozRepository;
    @Autowired
    private ImtiyozTypeService imtiyozTypeService;
    @Autowired
    private StudentService studentService;

    public ImtiyozDTO create(ImtiyozDTO dto) {
        return null;
    }

    public List<ImtiyozStudentDTO> getAll() {
        List<ImtiyozStudentDTO> studentDTOList = new LinkedList<>();
        for (ImtiyozEntity entity : imtiyozRepository.findAll()) {
            StudentDTO student = studentService.getStudentById(entity.getStudentId());
            if (student.getId() != 0) {
                ImtiyozStudentDTO imtiyozStudentDTO = new ImtiyozStudentDTO();
                imtiyozStudentDTO.setId(entity.getId());
                imtiyozStudentDTO.setStudentName(student.getName());
                imtiyozStudentDTO.setStudentSurname(student.getSurname());
                imtiyozStudentDTO.setTypeName(entity.getImtiyozType().getTypeName());
                studentDTOList.add(imtiyozStudentDTO);
            }
        }
        return studentDTOList;
    }

    public ImtiyozStudentDTO getById(Integer id) {
        ImtiyozEntity entity = get(id);
        StudentDTO student = studentService.getStudentById(entity.getStudentId());
        ImtiyozStudentDTO imtiyozStudentDTO = new ImtiyozStudentDTO();
        imtiyozStudentDTO.setId(entity.getId());
        imtiyozStudentDTO.setStudentName(student.getName());
        imtiyozStudentDTO.setStudentSurname(student.getSurname());
        imtiyozStudentDTO.setTypeName(entity.getImtiyozType().getTypeName());
        return imtiyozStudentDTO;
    }

    public void update(Integer id, ImtiyozDTO dto) {
        ImtiyozTypeEntity imtiyozTypeEntity = imtiyozTypeService.get(dto.getTypeId());
        ImtiyozEntity entity = get(id);
        entity.setImtiyozType(imtiyozTypeEntity);
        imtiyozRepository.save(entity);
    }

    public void delete(Integer id) {
        ImtiyozEntity entity = get(id);
        imtiyozRepository.delete(entity);
    }

    public ImtiyozDTO toDTO(ImtiyozEntity entity) {
        ImtiyozDTO dto = new ImtiyozDTO();
        dto.setId(entity.getId());
        dto.setTypeId(entity.getImtiyozType().getId());
        dto.setStudentId(entity.getStudentId());
        return dto;
    }

    public ImtiyozEntity get(Integer id) {
        return imtiyozRepository.findById(id).orElseThrow(() -> new RuntimeException("imtiyoz not found"));
    }
}
