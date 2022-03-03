package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.AddressEntity;
import com.projects.libraryproject.entity.PostEntity;
import com.projects.libraryproject.entity.RoleEntity;
import com.projects.libraryproject.entity.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> getAllUsers();

    UserEntity saveUser(UserEntity user);

    UserEntity updateUser(UserEntity user, AddressEntity address, PostEntity post, RoleEntity role);

    UserEntity getUserById(long id);

    void deleteUserById(long id);

}
