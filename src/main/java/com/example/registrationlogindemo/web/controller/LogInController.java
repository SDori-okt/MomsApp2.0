package com.example.registrationlogindemo.web.controller;

import com.example.registrationlogindemo.persistence.entity.UserEntity;
import com.example.registrationlogindemo.persistence.repository.UserRepository;
import com.example.registrationlogindemo.security.UserDetailsPrincipal;
import com.example.registrationlogindemo.service.LogInService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@Validated
@RequestMapping
@AllArgsConstructor
public class LogInController implements UserDetailsService {

    private UserRepository userRepository;
    private LogInService logInService;

    @GetMapping(path = "/login")
    public String logInPage(Model model) {
        model.addAttribute("user", new UserEntity());
        return "login";
    }

    @PostMapping(path = "/login")
    public String logIn(@ModelAttribute UserEntity user) {
        logInService.logIn(user);
        return "index";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);
        return new UserDetailsPrincipal(byUsername.get());
    }

}
