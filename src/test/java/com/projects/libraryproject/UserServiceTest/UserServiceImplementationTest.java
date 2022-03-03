package com.projects.libraryproject.UserServiceTest;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.repository.*;
import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserServiceImplementationTest {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PostRepository postRepository;
    private final LibraryRepository libraryRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImplementationTest(UserRepository userRepository, AddressRepository addressRepository,
                                         PostRepository postRepository, LibraryRepository libraryRepository,
                                         RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.postRepository = postRepository;
        this.libraryRepository = libraryRepository;
        this.roleRepository = roleRepository;
    }

    UserServiceImplementation userServiceImplementation;

    @BeforeEach
    void setUp() {
        userServiceImplementation = new UserServiceImplementation(userRepository, addressRepository,
                postRepository, libraryRepository,
                roleRepository);
    }

    @Test
    void getAllUsers() {
        var result = userServiceImplementation.getAllUsers();
        assertThat(result.size() == userServiceImplementation.getAllUsers().size()).isTrue();
    }

    @Test
    void saveUser() {
        PostEntity post = PostEntity.builder()
                .postLocality("Kobyłka")
                .zipCode("15-550")
                .build();

        AddressEntity address = AddressEntity.builder()
                .street("Wojciechowska")
                .post(postRepository
                        .save(post))
                .houseNumber(15)
                .houseUnitNumber(52)
                .locality("Wojciechowo")
                .build();

        RoleEntity role = RoleEntity.builder()
                .roleName("USER")
                .build();

        UserEntity user = UserEntity.builder()
                .firstName("Konrad")
                .lastName("Malinowski")
                .email("konrad@o2.pl")
                .password("ananas")
                .balance(0)
                .role(roleRepository.getRoleEntityByRoleName(role.getRoleName()).get())
                .library(libraryRepository.getById(1L))
                .address(addressRepository.save(address))
                .build();

        userRepository.save(user);
        System.out.println(user);
        System.out.println(userRepository.findAll());
    }

    @Test
    void updateStudent() {
        UserEntity existingUser = userRepository.getById(45L);
        PostEntity post = PostEntity.builder()
                .postLocality("Kobyłka")
                .zipCode("15-550")
                .build();

        AddressEntity address = AddressEntity.builder()
                .street("Wojciechowska")
                .post(postRepository
                        .save(post))
                .houseNumber(15)
                .houseUnitNumber(52)
                .locality("Wojciechowo")
                .build();

        RoleEntity role = RoleEntity.builder()
                .roleName("USER")
                .build();
        UserEntity user = UserEntity.builder()
                .firstName("Konrad")
                .lastName("Malinowski")
                .email("konrad@o2.pl")
                .password("ananas")
                .balance(0)
                .role(roleRepository.getRoleEntityByRoleName(role.getRoleName()).get())
                .library(libraryRepository.getById(1L))
                .address(addressRepository.save(address))
                .build();
        existingUser.setAddress(address);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userRepository.save(existingUser);
        System.out.println(existingUser);
        System.out.println(userRepository.findAll());
    }

    @Test
    void getUserById() {
        long id = 45L;
        System.out.println(userRepository.getById(id));
    }

    @Test
    void deleteUserById() {
        long id = 45L;
        userRepository.deleteById(id);
    }
}