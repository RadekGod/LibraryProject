package com.projects.libraryproject.mapper;

import com.projects.libraryproject.dto.AuthorDTO;
import com.projects.libraryproject.dto.BookDTO;
import com.projects.libraryproject.dto.TypeDTO;
import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AuthorMapper {

    public static List<AuthorEntity> mapFromDtoToEntity(AuthorDTO dto) {
        if (dto.getFirstName().contains(",")) {
            String[] firstNames = dto.getFirstName().split(",");
            String[] lastNames = dto.getLastName().split(",");
            List<AuthorEntity> authors = new ArrayList<>();
            for (int i = 0; i < lastNames.length; i++) {
                authors.add(new AuthorEntity(firstNames[i], lastNames[i]));
            }
            return authors;
        } else {
            return List.of(new AuthorEntity(dto.getFirstName(), dto.getLastName()));
        }
    }

    public static AuthorDTO mapFromEntityToDto(List<AuthorEntity> entity) {
        StringJoiner joiner = new StringJoiner(",");
        AuthorDTO dto = new AuthorDTO();
        System.out.println(entity);
        entity.stream()
                .map(AuthorEntity::getFirstName)
                .forEach(joiner::add);
        dto.setFirstName(joiner.toString());
        joiner = new StringJoiner(",");
        entity.stream()
                .map(AuthorEntity::getLastName)
                .forEach(joiner::add);
        dto.setLastName(joiner.toString());
        System.out.println(dto);
        return dto;
    }
}
