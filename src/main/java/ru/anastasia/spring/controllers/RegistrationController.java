package ru.anastasia.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anastasia.spring.models.Users;
import ru.anastasia.spring.service.UsersService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UsersService usersService;

    public RegistrationController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping()
    public String addUserForm(@ModelAttribute("userForm") Users users){
        return "security/registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userForm") Users users, Model model) {
        if(usersService.saveUser(users)){
            model.addAttribute("registrationSuccess", "Пользователь успешно зарегистрирован");
            return "security/login";
        } else {
            model.addAttribute("registrationError", "Пользователь с таким логином уже существует");
            return "security/registration";
        }
    }

}
