package com.projects.libraryproject;

import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import com.projects.libraryproject.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryProjectApplicationTests {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    LibraryProjectApplicationTests(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @BeforeEach
    public void init() {

    }

    @Test
    void shouldReturnListOfUserEntities() {
    }

}
