package com.projects.libraryproject.dto;

import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.InstitutionEntity;
import com.projects.libraryproject.entity.LibraryEntity;
import com.projects.libraryproject.entity.TypeEntity;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {

    private long bookId;

    private String title;

    private TypeEntity types;

    private AuthorEntity authors;

    private List<InstitutionEntity> institutions;

    private String  publisher;

    private String releaseDate;

    private String ISBN;

    private int pages;

    private String coverType;

    private String description;

    private LibraryEntity library;



}
