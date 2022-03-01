package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    public UserEntity saveUser(UserEntity user);

}
