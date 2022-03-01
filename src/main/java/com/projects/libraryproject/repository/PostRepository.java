package com.projects.libraryproject.repository;

import com.projects.libraryproject.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Optional<PostEntity> findPostEntityByZipCodeAndPostLocality(String zipCode, String postLocality);


}
