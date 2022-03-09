package com.projects.libraryproject.service.Implementation;

import com.projects.libraryproject.LoginUserDetails;
import com.projects.libraryproject.entity.UserEntity;
import com.projects.libraryproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {


    UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user= userRepository.findUserEntityByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return user.map(LoginUserDetails::new).get();
    }
}
