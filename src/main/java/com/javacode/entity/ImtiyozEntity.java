package com.javacode.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "imtiyoz")
public class ImtiyozEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer studentId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imtiyoz_type_id")
    private ImtiyozTypeEntity imtiyozType;
}
