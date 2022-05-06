package com.javacode.service;

import com.javacode.dto.ImtiyozTypeDTO;
import com.javacode.entity.ImtiyozTypeEntity;
import com.javacode.repository.ImtiyozTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImtiyozTypeService {
    @Autowired
    private ImtiyozTypeRepository imtiyozTypeRepository;

    public ImtiyozTypeDTO create(ImtiyozTypeDTO dto) {
        ImtiyozTypeEntity entity = new ImtiyozTypeEntity();
        entity.setTypeName(dto.getName());
        imtiyozTypeRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<ImtiyozTypeDTO> getAll() {
        List<ImtiyozTypeEntity> entityList = imtiyozTypeRepository.findAll();
        return entityList.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ImtiyozTypeDTO getById(Integer id) {
        ImtiyozTypeEntity entity = get(id);
        return toDTO(entity);
    }

    public void update(Integer id, ImtiyozTypeDTO dto) {
        ImtiyozTypeEntity entity = get(id);
        entity.setTypeName(dto.getName());
        imtiyozTypeRepository.save(entity);
    }

    public void delete(Integer id) {
        ImtiyozTypeEntity entity = get(id);
        imtiyozTypeRepository.delete(entity);
    }

    public ImtiyozTypeDTO toDTO(ImtiyozTypeEntity entity) {
        ImtiyozTypeDTO dto = new ImtiyozTypeDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getTypeName());
        return dto;
    }

    public ImtiyozTypeEntity get(Integer id) {
        return imtiyozTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("imtiyoz not found"));
    }
}
