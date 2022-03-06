package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
