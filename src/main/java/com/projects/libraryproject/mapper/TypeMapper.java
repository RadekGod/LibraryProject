package com.projects.libraryproject.mapper;

import com.projects.libraryproject.dto.BookDTO;
import com.projects.libraryproject.dto.TypeDTO;
import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class TypeMapper {

    public static List<TypeEntity> mapFromDtoToEntity(TypeDTO dto) {
        if (dto.getTypeName().contains(",")) {
            String[] typeNames = dto.getTypeName().split(",");
            List<TypeEntity> types = new ArrayList<>();
            for (String typeName : typeNames) {
                types.add(new TypeEntity(typeName));
            }
            return types;
        } else {
            return List.of(new TypeEntity( dto.getTypeName()));
        }
    }

    public static TypeDTO mapFromEntityToDto(List<TypeEntity> entity) {
        StringJoiner joiner = new StringJoiner(",");
        TypeDTO dto = new TypeDTO();
        System.out.println(entity);
        entity.stream()
                .map(TypeEntity::getTypeName)
                .forEach(joiner::add);
        dto.setTypeName(joiner.toString());
        return dto;
    }
}
