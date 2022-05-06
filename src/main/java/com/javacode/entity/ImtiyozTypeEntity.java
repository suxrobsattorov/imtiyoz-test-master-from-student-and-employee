package com.javacode.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "imtiyoz_type")
public class ImtiyozTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String typeName;

    @OneToMany(mappedBy = "imtiyozType")
    private List<ImtiyozEntity> imtiyozEntityList;
}
