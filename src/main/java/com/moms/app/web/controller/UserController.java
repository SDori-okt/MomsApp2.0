package com.moms.app.web.controller;

import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.service.UserService;
import com.moms.app.web.model.CreateUserRequest;
import com.moms.app.web.model.PagingSortingFilteringRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public String registerUser(@Valid @ModelAttribute("user") CreateUserRequest createUserRequest, Model model) throws Exception {
        model.addAttribute("user", createUserRequest);
        try {
            userService.addUser(createUserRequest);
        } catch (Exception e) {
            return "register_unsuccess";
        }
        return "register_success";
    }


    @GetMapping("/profile")
    public String updateUserPersonalDataForm(Model model) {
        if (logInCheck()) {
            return "login";
        }

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserEntity> byUserName = userService.findByUserName(name);

//        model.addAttribute("first_name",byUserName.get().getFirstName());
//        model.addAttribute("last_name",byUserName.get().getLastName());
//        model.addAttribute("date_of_birth",byUserName.get().getDateOfBirth());
//        model.addAttribute("város",byUserName.get().getLocation());
//        model.addAttribute("street",byUserName.get().getStreet());
//        model.addAttribute("housenumber",byUserName.get().getHouseNumber());

        model.addAttribute("user", new UserEntity());
        return "edit_profile";
    }

    @PutMapping("/personal/{id}")
    @ResponseStatus(HttpStatus.OK)
//    @RolesAllowed("ADMIN", "USER")
    public void updateUserPersonalData(@ModelAttribute CreateUserRequest createUserRequest, Model model) {
        model.addAttribute("user", createUserRequest);
//        userService.updateUserPersonalData(id, createUserRequest);
    }

    private boolean logInCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }


    //Endpoints for Postman
    //------------------------------------------------------------------------------------------------------------------

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




    @GetMapping("/users")
    public String getUsers(Model model) {
        List<UserEntity> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

}
