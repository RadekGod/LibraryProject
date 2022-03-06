package com.projects.libraryproject.service.Implementation;

import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;
import com.projects.libraryproject.repository.*;
import com.projects.libraryproject.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImplementation implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final InstitutionRepository institutionRepository;
    private final TypeRepository typeRepository;
    private final LibraryRepository libraryRepository;

    public BookServiceImplementation(AuthorRepository authorRepository, BookRepository bookRepository,
                                     InstitutionRepository institutionRepository,
                                     TypeRepository typeRepository, LibraryRepository libraryRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.institutionRepository = institutionRepository;
        this.typeRepository = typeRepository;
        this.libraryRepository = libraryRepository;
    }


    @Override
    public List<BookEntity> getAllBooks() {
        System.out.println(bookRepository.findAll());
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public BookEntity saveBook(BookEntity bookEntity, List<AuthorEntity> authorEntity, List<TypeEntity> typeEntity) {
        List<TypeEntity> types = typeRepository.findAll();
        List<TypeEntity> notExistingTypes = getNotExistingTypes(typeEntity, types);
        List<TypeEntity> resultTypes = getExistingTypesFromDatabase(typeEntity, types);

        List<AuthorEntity> authors = authorRepository.findAll();
        List<AuthorEntity> notExistingAuthors = getNotExistingAuthors(authorEntity, authors);
        List<AuthorEntity> resultAuthors = getExistingAuthorsFromDatabase(authorEntity, authors);
        System.out.println("Autorzy: " + notExistingAuthors);


        bookEntity.setLibrary(libraryRepository.getById(1L));
        resultAuthors.addAll(authorRepository.saveAll(notExistingAuthors));
        resultTypes.addAll(typeRepository.saveAll(notExistingTypes));
        System.out.println(resultAuthors);
        System.out.println(resultTypes);
        bookEntity.setTypes(resultTypes);
        bookEntity.setAuthors(resultAuthors);
        bookRepository.save(bookEntity);
        return bookEntity;
    }

    private List<AuthorEntity> getExistingAuthorsFromDatabase(List<AuthorEntity> authorEntity, List<AuthorEntity> authors) {
        List<AuthorEntity> resultAuthors = new ArrayList<>();
        List<AuthorEntity> existingAuthors = authorEntity.stream()
                .filter(authors::contains)
                .collect(Collectors.toList());

        for (AuthorEntity author : existingAuthors) {
            for (AuthorEntity entity : authors) {
                if (author.equals(entity)) {
                    resultAuthors.add(entity);
                }
            }
        }
        return resultAuthors;
    }

    private List<AuthorEntity> getNotExistingAuthors(List<AuthorEntity> authorEntity, List<AuthorEntity> authors) {
        return authorEntity.stream()
                .filter(author -> !authors.contains(author))
                .collect(Collectors.toList());
    }
    private List<TypeEntity> getExistingTypesFromDatabase(List<TypeEntity> typeEntity, List<TypeEntity> types) {
        List<TypeEntity> existingTypes = typeEntity.stream()
                .filter(types::contains)
                .collect(Collectors.toList());
        List<TypeEntity> resultTypes = new ArrayList<>();
        for (TypeEntity type : existingTypes) {
            for (TypeEntity entity : types) {
                if (type.equals(entity)) {
                    resultTypes.add(entity);
                }
            }
        }

        return resultTypes;
    }

    private List<TypeEntity> getNotExistingTypes(List<TypeEntity> typeEntity, List<TypeEntity> types) {
        return typeEntity.stream()
                .filter(type -> !types.contains(type))
                .collect(Collectors.toList());
    }

    @Override
    public BookEntity getBookById(long id) {
        return bookRepository.findById(id).get();
    }


    @Override
    @Transactional
    public void updateBook(long id, BookEntity bookEntity, List<AuthorEntity> authorEntity, List<TypeEntity> typeEntity) {
        BookEntity existingBook = bookRepository.findById(id).get();
        bookEntity.setLibrary(existingBook.getLibrary());
        bookEntity.setBookId(id);
        List<TypeEntity> types = typeRepository.findAll();
        List<TypeEntity> notExistingTypes = getNotExistingTypes(typeEntity, types);
        List<TypeEntity> resultTypes = getExistingTypesFromDatabase(typeEntity, types);

        List<AuthorEntity> authors = authorRepository.findAll();
        List<AuthorEntity> notExistingAuthors = getNotExistingAuthors(authorEntity, authors);
        List<AuthorEntity> resultAuthors = getExistingAuthorsFromDatabase(authorEntity, authors);

        resultAuthors.addAll(authorRepository.saveAll(notExistingAuthors));
        resultTypes.addAll(typeRepository.saveAll(notExistingTypes));

        bookEntity.setAuthors(resultAuthors);
        bookEntity.setTypes(resultTypes);
        bookRepository.save(bookEntity);

    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

}
