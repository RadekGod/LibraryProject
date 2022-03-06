package com.projects.libraryproject.controller;


import com.projects.libraryproject.dto.AuthorDTO;
import com.projects.libraryproject.dto.BookDTO;
import com.projects.libraryproject.dto.TypeDTO;
import com.projects.libraryproject.entity.AuthorEntity;
import com.projects.libraryproject.entity.BookEntity;
import com.projects.libraryproject.entity.TypeEntity;
import com.projects.libraryproject.mapper.AuthorMapper;
import com.projects.libraryproject.mapper.BookMapper;
import com.projects.libraryproject.mapper.TypeMapper;
import com.projects.libraryproject.service.Implementation.BookServiceImplementation;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {


    private final BookServiceImplementation bookServiceImplementation;

    @Autowired
    public BookController(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookServiceImplementation.getAllBooks());
        return "books";
    }

    @GetMapping("/new")
    public String createBookFrom(Model model) {
        BookDTO book = new BookDTO();
        AuthorDTO author = new AuthorDTO();
        TypeDTO type = new TypeDTO();
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        model.addAttribute("type", type);
        return "create_book";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute("book") BookDTO book, @ModelAttribute("author") AuthorDTO author,
                           @ModelAttribute("type") TypeDTO type) {
        List<AuthorEntity> authors = AuthorMapper.mapFromDtoToEntity(author);
        List<TypeEntity> types = TypeMapper.mapFromDtoToEntity(type);
        BookEntity bookEntity = BookMapper.mapFromDtoToEntity(book, authors, types);
        bookServiceImplementation.saveBook(bookEntity, authors, types);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookFrom(@PathVariable long id, Model model) {
        BookEntity bookEntity = bookServiceImplementation.getBookById(id);

        model.addAttribute("book", bookEntity);
        model.addAttribute("author", AuthorMapper.mapFromEntityToDto(bookEntity.getAuthors()));
        model.addAttribute("type", TypeMapper.mapFromEntityToDto(bookEntity.getTypes()));

        return "edit_book";
    }

    @PostMapping("/{id}")
    public String updateBook(@PathVariable long id, Model model,
                             @ModelAttribute("book") BookDTO book, @ModelAttribute("author") AuthorDTO author,
                             @ModelAttribute("type") TypeDTO type) {
        List<AuthorEntity> authors = AuthorMapper.mapFromDtoToEntity(author);
        List<TypeEntity> types = TypeMapper.mapFromDtoToEntity(type);
        BookEntity bookEntity = BookMapper.mapFromDtoToEntity(book, authors, types);
        bookServiceImplementation.updateBook(id, bookEntity, authors, types);

        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id) {
        bookServiceImplementation.deleteBookById(id);
        return "redirect:/books";
    }

}
