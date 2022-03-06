package com.projects.libraryproject.mapper;

import com.projects.libraryproject.dto.BookDTO;
import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {

    public static BookEntity mapFromDtoToEntity(BookDTO bookDTO, List<AuthorEntity> authors, List<TypeEntity> types) {
        System.out.println(authors);
        System.out.println(types);
        System.out.println(bookDTO);
        BookEntity entity = null;
        bookDTO.setReleaseDate(bookDTO.getReleaseDate().replace("-", "/"));
        try {
            entity = BookEntity.builder()
                    .title(bookDTO.getTitle())
                    .releaseDate(new SimpleDateFormat("yyyy/MM/dd").parse(bookDTO.getReleaseDate()))
                    .ISBN(bookDTO.getISBN())
                    .pages(bookDTO.getPages())
                    .coverType(bookDTO.getCoverType())
                    .description(bookDTO.getDescription())
                    .publisher(bookDTO.getPublisher())
                    .authors(authors)
                    .types(types)
                    .build();
            System.out.println(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return entity;
    }

    public static List<AuthorEntity> createListOfAuthors(AuthorEntity authors) {
        if (authors.getFirstName().contains(",")) {
            List<String> firstNames = List.of(authors.getFirstName().split(","));
            List<String> lastNames = List.of(authors.getLastName().split(","));
            List<AuthorEntity> authorEntities = new ArrayList<>();
            for (int i = 0; i < firstNames.size(); i++) {
                authorEntities.add(new AuthorEntity(firstNames.get(i), lastNames.get(i)));
            }
            System.out.println(authorEntities);
            return authorEntities;
        } else {
            return List.of(authors);
        }

    }
}
