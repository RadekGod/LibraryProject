package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.LibraryEntity;
import com.projects.libraryproject.entity.UserEntity;

public interface LibraryService {

    LibraryEntity getLibrary(long id);

    LibraryEntity saveLibrary(LibraryEntity libraryEntity);

}
