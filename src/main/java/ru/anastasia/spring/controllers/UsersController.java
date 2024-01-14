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

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/edit") //редактирование пользователя
    public String editNameForm (HttpSession session, Model model){
        Users users = (Users)session.getAttribute("userInfo");
        model.addAttribute("user",users);
        return "users/editUser";
    }

    @PatchMapping("/edit") //редактирование пользователя
    public String editName(@ModelAttribute("user") Users user,
                           @RequestParam("newPassword") String newPassword,
                           HttpSession session,
                           Model model){
        if(newPassword.equals(user.getPassword())){
            if(usersService.updateUser(user, session)){
                session.setAttribute("userInfo", user);
                return "redirect:/folders";
            } else {
                model.addAttribute("error", "Пользователь с таким логином уже существует");
            }
        } else {
            model.addAttribute("error", "Пароли не совпадают");
        }
        return "users/editUser";
    }

}
