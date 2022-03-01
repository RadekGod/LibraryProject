package com.projects.libraryproject.service;

import com.projects.libraryproject.entity.PostEntity;
import com.projects.libraryproject.entity.UserEntity;

public interface PostService {

    PostEntity getPostByZipCode(String zipCode);

    PostEntity savePost(PostEntity post);
}
