package com.projects.libraryproject.controller.unauthenticated;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class UnauthenticatedBookController {


    private final BookServiceImplementation bookServiceImplementation;

    @Autowired
    public UnauthenticatedBookController(BookServiceImplementation bookServiceImplementation) {
        this.bookServiceImplementation = bookServiceImplementation;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookServiceImplementation.getAllBooks());
        return "/books";
    }


}
