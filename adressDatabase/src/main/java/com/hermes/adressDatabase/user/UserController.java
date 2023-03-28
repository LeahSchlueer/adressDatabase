package com.hermes.adressDatabase.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/sign-in")
    String login() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    String registerNew(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String register(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(System.out::println);
        } else {
            userService.signUpUser(user);
        }
        return registerNew(model);


    }
}
