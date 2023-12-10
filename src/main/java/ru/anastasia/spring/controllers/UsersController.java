package ru.anastasia.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anastasia.spring.models.Users;
import ru.anastasia.spring.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

    final
    UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/edit") //изменение имени пользователя
    public String editNameForm (HttpSession session, Model model){
        Users users = (Users)session.getAttribute("userInfo");
        model.addAttribute("user",users);
        return "/users/editUser";
    }

    @PatchMapping("/edit") //изменение имени пользователя
    public String editName(@ModelAttribute("user") Users user, HttpSession session){
        session.setAttribute("userInfo", user);
        usersService.setNewName(user);
        return "redirect:/folders/folder";
    }

}
