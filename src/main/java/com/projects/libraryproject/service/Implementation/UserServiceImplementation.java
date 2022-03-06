
package com.projects.libraryproject.service.Implementation;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.repository.*;
import com.projects.libraryproject.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PostRepository postRepository;
    private final LibraryRepository libraryRepository;
    private final RoleRepository roleRepository;

    public UserServiceImplementation(UserRepository userRepository, AddressRepository addressRepository,
                                     PostRepository postRepository, LibraryRepository libraryRepository,
                                     RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.postRepository = postRepository;
        this.libraryRepository = libraryRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity user) {
        user.setLibrary(libraryRepository.getById(1L));
        Optional<PostEntity> post = postRepository.findPostEntityByZipCodeAndPostLocality(user.getAddress().getPost().getZipCode(),
                user.getAddress().getPost().getPostLocality());

        Optional<RoleEntity> existingRole = roleRepository.getRoleEntityByRoleName(user.getRole().getRoleName());

        if (existingRole.isPresent()) {
            RoleEntity roleResult = existingRole.get();
            user.setRole(roleRepository.save(roleResult));
        } else {
            roleRepository.save(user.getRole());
        }

        if (post.isPresent()) {
            PostEntity postResult = post.get();
            postRepository.save(postResult);
            user.getAddress().setPost(postResult);
        } else {
            postRepository.save(user.getAddress().getPost());
        }

        Optional<AddressEntity> existingAddress = addressRepository.getAddressEntityByLocalityAndStreetAndHouseNumberAndHouseUnitNumberAndPost(user.getAddress().getLocality(),
                user.getAddress().getStreet(), user.getAddress().getHouseNumber(), user.getAddress().getHouseUnitNumber(), user.getAddress().getPost());

        if (existingAddress.isPresent()) {
            AddressEntity addressResult = existingAddress.get();
            user.setAddress(addressRepository.save(addressResult));
        } else {
            addressRepository.save(user.getAddress());
        }

        return userRepository.save(user);
    }


    @Override
    @Transactional
    public UserEntity updateUser(UserEntity user, AddressEntity address, PostEntity post, RoleEntity role) {
        System.out.println("zedytowany" + user);
        System.out.println("zedytowany" + address);
        System.out.println("zedytowany" + post);
        System.out.println("zedytowany" + role);




        UserEntity existingUser = getUserById(user.getUserId());

        //ustawienie placówki pocztowej w zależności od tego czy istnieje w bazie
        Optional<RoleEntity> existingRole = roleRepository.getRoleEntityByRoleName(role.getRoleName());

        if (existingRole.isPresent()) {
            RoleEntity roleResult = existingRole.get();
            user.setRole(roleRepository.save(roleResult));
        } else {
            user.setRole(roleRepository.save(role));
        }

        Optional<PostEntity> existingPost = postRepository.findPostEntityByZipCodeAndPostLocality(post.getZipCode(),
                post.getPostLocality());

        if (existingPost.isPresent()) {
            PostEntity postResult = existingPost.get();
            address.setPost(postRepository.save(postResult));
        } else {
            address.setPost(postRepository.save(post));
        }

        Optional<AddressEntity> existingAddress = addressRepository.getAddressEntityByLocalityAndStreetAndHouseNumberAndHouseUnitNumberAndPost(address.getLocality(),
                address.getStreet(), address.getHouseNumber(), address.getHouseUnitNumber(), address.getPost());

        if (existingAddress.isPresent()) {
            AddressEntity addressResult = existingAddress.get();
            user.setAddress(addressRepository.save(addressResult));
        } else {
            user.setAddress(addressRepository.save(address));
        }

        user.setLibrary(existingUser.getLibrary());
        //existingUser.setAddress(addressRepository.save(user.getAddress()));
        return userRepository.save(user);
    }
    @Override
    public UserEntity getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
