package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;

import java.util.List;

public interface BookService {

    List<BookEntity> getAllBooks();

    BookEntity saveBook(BookEntity bookEntity, List<AuthorEntity> authorEntity, List<TypeEntity> typeEntity);

    BookEntity getBookById(long id);

    void updateBook(long id, BookEntity bookEntity, List<AuthorEntity> authors, List<TypeEntity> types);
    void deleteBookById(long id);
}
