package com.javacode.repository;

import com.javacode.entity.ImtiyozEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImtiyozRepository extends JpaRepository<ImtiyozEntity, Integer> {
}
