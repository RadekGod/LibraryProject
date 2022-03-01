package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface LibraryRepository extends JpaRepository<LibraryEntity, Long> {



}
