package com.example.registrationlogindemo.service.mapper;

import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.example.registrationlogindemo.persistence.entity.UserRole;
import com.example.registrationlogindemo.web.model.CreateUserRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserMapper {

    public UserEntity map(CreateUserRequest createUserRequest) {
        return UserEntity.builder()
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .email(createUserRequest.getEmail())
                .username(createUserRequest.getUsername())
                .password(createUserRequest.getPassword())
                .dateOfBirth(createUserRequest.getDateOfBirth())
                .location(createUserRequest.getLocation())
                .street(createUserRequest.getStreet())
                .houseNumber(createUserRequest.getHouseNumber())
                .roles(Collections.singleton(UserRole.USER))
                .build();
    }
    
}

