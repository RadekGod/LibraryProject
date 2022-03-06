package com.projects.libraryproject.dto;

import com.projects.libraryproject.entity.BookEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class TypeDTO {

    private long typeId;

    private String typeName;

    private List<BookEntity> books;

}
