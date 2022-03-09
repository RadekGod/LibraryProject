package com.projects.libraryproject.BookServiceTest;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.repository.*;
import com.projects.libraryproject.service.Implementation.BookServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
public class BookServiceImplementationTest {

    BookRepository bookRepository;
    InstitutionRepository institutionRepository;
    TypeRepository typeRepository;
    AuthorRepository authorRepository;
    BookServiceImplementation bookServiceImplementation;
    LibraryRepository libraryRepository;

    @Autowired
    public BookServiceImplementationTest(BookRepository bookRepository, InstitutionRepository institutionRepository,
                                         TypeRepository typeRepository, AuthorRepository authorRepository,
                                         LibraryRepository libraryRepository) {
        this.bookRepository = bookRepository;
        this.institutionRepository = institutionRepository;
        this.typeRepository = typeRepository;
        this.authorRepository = authorRepository;
        this.libraryRepository = libraryRepository;
    }


    @BeforeEach
    void setUp() {
        bookServiceImplementation = new BookServiceImplementation(authorRepository, bookRepository,
                institutionRepository, typeRepository, libraryRepository);
    }

    @Test
    void getAllBooks() {
        var result = bookServiceImplementation.getAllBooks();
        System.out.println(result);
        assertThat(result.size() == bookServiceImplementation.getAllBooks().size()).isTrue();
    }

    @Test
    void saveBook() {
        LibraryEntity library = libraryRepository.getById(1L);
        AuthorEntity author = new AuthorEntity("Konrad", "Łyda");
        TypeEntity type = new TypeEntity("Autobiografia");
        try {
            BookEntity book = BookEntity.builder()
                    .title("Jak być zjebem")
                    .authors(List.of(author))
                    .releaseDate(new SimpleDateFormat("dd/MM/yy").parse("04/03/2022"))
                    .pages(2137)
                    .coverType("Miekka")
                    .ISBN("9181328324800")
                    .description("Duża książka autorstwa małego człowieka")
                    .library(library)
                    .types(List.of(type))
                    .publisher("Sakała Industries")
                    .build();

            authorRepository.save(author);
            typeRepository.save(type);
            bookRepository.save(book);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    void getBookById() {
        System.out.println(bookServiceImplementation.getBookById(16L));
    }

    @Test
    void updateBook() {
        BookEntity existingBook = bookServiceImplementation.getBookById(16L);
        existingBook.setAuthors(List.of(new AuthorEntity("Jan", "Brzechwa")));
        bookServiceImplementation.saveBook(existingBook, existingBook.getAuthors(), existingBook.getTypes());
    }

    @Test
    void deleteBook() {
        bookServiceImplementation.deleteBookById(18L);
    }

}
