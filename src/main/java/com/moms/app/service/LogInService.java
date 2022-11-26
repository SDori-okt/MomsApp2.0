package com.moms.app.service;

import com.moms.app.configuration.MyAuth;
import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.persistence.repository.UserRepository;
import com.moms.app.security.UserDetailsPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LogInService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails logIn(UserEntity user) {
        Optional<UserEntity> maybeUser = userRepository.findByUsername(user.getUsername());

        if (maybeUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", maybeUser.get().getUsername()));
        } else {
            boolean matches = new BCryptPasswordEncoder().matches(user.getPassword(), maybeUser.get().getPassword());
            if (matches) {
                SecurityContextHolder.getContext().setAuthentication(new MyAuth(user.getUsername()));
                return loadUserByUsername(user.getUsername());
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        return new UserDetailsPrincipal(byUsername.get());
    }
}
