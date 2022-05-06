package com.javacode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private String level;
    private String username;
    private String password;
    private Integer groupId;
    private Collection<RoleDTO> roleDTOS = new ArrayList<>();
}
