
package com.projects.libraryproject.service.Implementation;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.repository.*;
import com.projects.libraryproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PostRepository postRepository;
    private final LibraryRepository libraryRepository;
    private final RoleRepository roleRepository;

    public UserServiceImplementation(UserRepository userRepository, AddressRepository addressRepository,
                                     PostRepository postRepository, LibraryRepository libraryRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.postRepository = postRepository;
        this.libraryRepository = libraryRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> getAllUsers() {
        System.out.println(postRepository.findPostEntityByZipCodeAndPostLocality("01-029", "Warszawa"));

        return userRepository.findAll();
    }

    @Transactional
    public UserEntity saveUser(UserEntity user) {
        RoleEntity role = new RoleEntity(1L, "USER", "Standardowy użytkownik");
        user.setBalance(0);
        user.setLibrary(libraryRepository.getById(1L));
        Optional<PostEntity> post = postRepository.findPostEntityByZipCodeAndPostLocality(user.getAddress().getPost().getZipCode(),
                user.getAddress().getPost().getPostLocality());
        if (post.isPresent()) {
            PostEntity postResult = post.get();
            postRepository.save(postResult);
            user.getAddress().setPost(postResult);
        } else {
            postRepository.save(user.getAddress().getPost());
        }

        addressRepository.save(user.getAddress());
        user.setRole(roleRepository.save(role));
        return userRepository.save(user);
    }
}
