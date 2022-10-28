package com.example.registrationlogindemo.controller.service.impl;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.controller.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setYearOfBirth(userDto.getYearOfBirth());
        user.setPostalCode(userDto.getPostalCode());
        user.setLocation(userDto.getLocation());
        user.setStreet(userDto.getStreet());
        user.setHouse_number(userDto.getHouse_number());

        user.setChildName(userDto.getChildName());
        user.setChildGender(userDto.getChildGender());
        user.setChildDateOfBirth(userDto.getChildDateOfBirth());
        user.setWhatFor(userDto.getWhatFor());
        user.setWhenTo(userDto.getWhenTo());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUsername(user.getUsername());
        userDto.setYearOfBirth(user.getYearOfBirth());
        userDto.setPostalCode(user.getPostalCode());
        userDto.setLocation(user.getLocation());
        userDto.setStreet(user.getStreet());
        userDto.setHouse_number(user.getHouse_number());

        userDto.setChildName(user.getChildName());
        userDto.setChildGender(user.getChildGender());
        userDto.setChildDateOfBirth(user.getChildDateOfBirth());
        userDto.setWhatFor(user.getWhatFor());
        userDto.setWhenTo(user.getWhenTo());

        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
