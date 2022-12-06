package com.moms.app.web.controller;

import com.moms.app.persistence.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Validated
@RequestMapping
@AllArgsConstructor
public class LogInController extends AbstractController {

    @GetMapping("/index")
    public String indexPage() {return "index";}

    @GetMapping(path = "/login")
    public String logInPage(Model model) {
        if (logInCheck()) {
            model.addAttribute("user", new UserEntity());
            return "login";
        }
        return "redirect:/index";
    }
}