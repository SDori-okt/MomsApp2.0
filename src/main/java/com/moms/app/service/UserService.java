package com.moms.app.service;

import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.persistence.repository.UserRepository;
import com.moms.app.service.mapper.UserMapper;
import com.moms.app.web.model.CreateUserRequest;
import com.moms.app.web.model.PagingSortingFilteringRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserEntity addUser(CreateUserRequest createUserRequest) throws Exception {
        String email = createUserRequest.getEmail();
        Optional<UserEntity> maybeUserEmail = userRepository.findByEmail(email);
        if (maybeUserEmail.isPresent()) {

            throw new Exception(String.format("User e-mail address already exists: '%s'", email));
        }

        String userName = createUserRequest.getUsername();
        Optional<UserEntity> maybeUserName = userRepository.findByUsername(userName);
        if (maybeUserName.isPresent()) {
            throw new Exception(String.format("User name already used: '%s'", userName));
        }

        UserEntity user = userMapper.map(createUserRequest);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void softDelete(long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
        } else {
            userRepository.deleteById(id);
        }
    }

    public Optional<UserEntity> updateUserPersonalData(String userName, CreateUserRequest createUserRequest) {
        Optional<UserEntity> maybeUserEntity = userRepository.findByUsername(userName);
        Optional<UserEntity> existingEmail = userRepository.findByEmail(createUserRequest.getEmail());

        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with user name: %s", userName));
        } else if (existingEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User e-mail address already exists: %s", existingEmail.get().getEmail()));
        }
        return Optional.of(userRepository.save(updateUserPersonalData(maybeUserEntity.get(), createUserRequest)));
    }

//    public Optional<UserEntity> updateUserPassword(long id, CreateUserRequest createUserRequest) {
//        Optional<UserEntity> maybeUserEntity = userRepository.findById(id);
//
//        if (maybeUserEntity.isEmpty()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with id: %s", id));
//        }
//        return Optional.of(userRepository.save(updateUserPassword(maybeUserEntity.get(), createUserRequest)));
//    }

    public List<UserEntity> findAllUser(PagingSortingFilteringRequest pagingSortingFilteringRequest) {
        List<UserEntity> listedUsers = new ArrayList<>();

        final Pageable pageable = PageRequest.of(pagingSortingFilteringRequest.getPage(),
                pagingSortingFilteringRequest.getSize(),
                Sort.by(Sort.Direction.valueOf(pagingSortingFilteringRequest.getSorting().toString()),
                        pagingSortingFilteringRequest.getSort().toString()));

        UserEntity entityAsProbe = UserEntity.builder().firstName(pagingSortingFilteringRequest.getSearch()).build();

        ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<UserEntity> example = Example.of(entityAsProbe, customExampleMatcher);

        final Page<UserEntity> page = userRepository.findAll(example, pageable);
        for (UserEntity userEntity : page) {
            listedUsers.add(userEntity);
        }
        return listedUsers;
    }

    private UserEntity updateUserPersonalData(UserEntity current, CreateUserRequest createUserRequest) {
        current.setFirstName(createUserRequest.getFirstName());
        current.setLastName(createUserRequest.getLastName());
        current.setDateOfBirth(createUserRequest.getDateOfBirth());
        current.setEmail(createUserRequest.getEmail());
        current.setLocation(createUserRequest.getLocation());
        current.setStreet(createUserRequest.getStreet());
        current.setHouseNumber(createUserRequest.getHouseNumber());
        return current;
    }

    public List<UserEntity> findByKeyword(String keyword) {
        if(keyword.isBlank()){
            return userRepository.findAll();
        }
        List<UserEntity> list = new ArrayList<>();
        for(UserEntity u : userRepository.findAll()){
            if(u.getUsername().contains(keyword) || u.getLocation().contains(keyword)
            || u.getFirstName().contains(keyword) || u.getLastName().contains(keyword)){
                list.add(u);
            }
        }
        return list;
    }

    public Optional<UserEntity> findByUserName(String userName) {
        Optional<UserEntity> maybeUserEntity = userRepository.findByUsername(userName);
        if (maybeUserEntity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User not found with user name: %s", userName));
        }
        return maybeUserEntity;
    }

//    private UserEntity updateUserPassword(UserEntity current, CreateUserRequest createUserRequest) {
//        current.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
//        return current;
//    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}
