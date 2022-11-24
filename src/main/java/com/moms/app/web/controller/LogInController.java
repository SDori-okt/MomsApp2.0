package com.moms.app.web.controller;

import com.moms.app.persistence.entity.UserEntity;
import com.moms.app.service.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping
@AllArgsConstructor
public class LogInController {

    private LogInService logInService;

    @GetMapping(path = "/")
    public String indexPageRoot() {
        if (logInCheck()) {
            return "login";
        }
        return "index";
    }

    @GetMapping(path = "/index")
    public String indexPage() {
        if (logInCheck()) {
            return "login";
        }
        return "index";
    }

    @GetMapping(path = "/login")
    public String logInPage(Model model) {
        if (logInCheck()) {
            model.addAttribute("user", new UserEntity());
            return "login";
        }
        return "index";
    }

    @PostMapping(path = "/login")
    public String logIn(@ModelAttribute UserEntity user) {
        logInService.logIn(user);
        return "index";
    }

    private boolean logInCheck() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication instanceof AnonymousAuthenticationToken;
    }

}
