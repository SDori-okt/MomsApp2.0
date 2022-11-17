package com.example.registrationlogindemo.web.controller;

import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.example.registrationlogindemo.service.UserService;
import com.example.registrationlogindemo.web.model.CreateUserRequest;
import com.example.registrationlogindemo.web.model.PagingSortingFilteringRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@Validated
@RequestMapping
public class UserController {
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping(path = "/register_success")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@ModelAttribute("user") CreateUserRequest createUserRequest, Model model) {
        model.addAttribute("user", createUserRequest);
        try {
            userService.addUser(createUserRequest);
        } catch (Exception e) {
            return "register_unsuccess";
        }
        return "register_success";
    }

    //Endpoints for Postman
    //------------------------------------------------------------------------------------------------------------------

    // Endpoint for Postman test
    @PostMapping(path = "/api/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUserPostman(@Valid @RequestBody CreateUserRequest createUserRequest) throws Exception {
        userService.addUser(createUserRequest);
    }

    // Endpoint for Postman test
    @DeleteMapping("/api/deleteUser/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @RolesAllowed("ADMIN", "USER")
    public void softDelete(@PathVariable("id") long id) {
        userService.softDelete(id);
    }

    // Endpoint for Postman test
    @PutMapping("/api/personal/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @RolesAllowed("ADMIN", "USER")
    public void updateUserPersonalData(@PathVariable("id") long id,
                                       @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUserPersonalData(id, createUserRequest);
    }

    // Endpoint for Postman test
    @PutMapping("/api/logIn/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @RolesAllowed("ADMIN", "USER")
    public void updateUserLogInData(@PathVariable("id") long id,
                                    @RequestBody CreateUserRequest createUserRequest) {
        userService.updateUserPassword(id, createUserRequest);
    }

    // Endpoint for Postman test
    @GetMapping(path = "/api/findAllUsers")
    @ResponseStatus(HttpStatus.OK)
//    @RolesAllowed("ADMIN")
    public List<UserEntity> findAllUsers(
            @RequestParam(defaultValue = "0") @Digits(integer = 5, fraction = 0) @PositiveOrZero Integer page,
            @RequestParam(defaultValue = "5") @Digits(integer = 5, fraction = 0) @PositiveOrZero Integer size,
            @RequestParam(defaultValue = "ASC") PagingSortingFilteringRequest.Sorting sorting,
            @RequestParam(defaultValue = "DEFAULT") PagingSortingFilteringRequest.Sort sort,
            @RequestParam(defaultValue = "") String search
    ) {
        return userService.findAllUser(PagingSortingFilteringRequest.builder().page(page).size(size).sorting(sorting).sort(sort).search(search).build());
    }

}
