package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.InstitutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstitutionRepository extends JpaRepository<InstitutionEntity, Long> {
}
