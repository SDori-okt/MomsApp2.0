package com.moms.app.service;

import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.persistence.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LogInService {

    private UserRepository userRepository;

    public void logIn(UserEntity user) {
        Optional<UserEntity> maybeUser = userRepository.findByUsername(user.getUsername());

        if (maybeUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", maybeUser.get().getUsername()));
        } else {
            boolean matches = new BCryptPasswordEncoder().matches(user.getPassword(), maybeUser.get().getPassword());
            if (matches) {
                // todo load user
            } else {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        }
    }

}
