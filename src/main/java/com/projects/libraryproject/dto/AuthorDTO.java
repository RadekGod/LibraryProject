package com.projects.libraryproject.dto;

import com.projects.libraryproject.entity.BookEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
public class AuthorDTO {

    private String firstName;

    private String lastName;

}
