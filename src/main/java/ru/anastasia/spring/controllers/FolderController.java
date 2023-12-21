package ru.anastasia.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Users;
import ru.anastasia.spring.service.FolderService;
import ru.anastasia.spring.service.UsersService;

@Controller
@RequestMapping("/folders")
public class FolderController {
    final UsersService usersService;
    final FolderService folderService;

    public FolderController(UsersService usersService, FolderService folderService) {
        this.folderService = folderService;
        this.usersService = usersService;
    }

    @GetMapping() //главная страница
    public String getMainPage(HttpSession session, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users currentPerson = usersService.getByLogin(user.getUsername());
        session.setAttribute("userInfo", currentPerson);
        model.addAttribute("user",currentPerson);
        model.addAttribute("folders",currentPerson.getFolderList());
        return "home";
    }

    @GetMapping("/new") //создание набора слов
    public String addFolder(@ModelAttribute("folder") Folder folder){
        return "/folder/addFolder";
    }

    @PostMapping //создание набора слов
    public String creatingFolder(@ModelAttribute("folder") Folder folder, HttpSession session) {
        folder.setIdUserFK((Users)session.getAttribute("userInfo"));
        folderService.save(folder);
        return "redirect:/folders";
    }

    @PreAuthorize("@folderService.getById(#id).idUserFK.login == authentication.name")
    @GetMapping("/{id}/edit") //редактирование набора слов
    public String editFolderForm(@PathVariable Long id, Model model) {
        model.addAttribute("folder",folderService.getById(id));
        return "/folder/editFolder";
    }

    @PatchMapping("/{id}") //редактирование набора слов
    public String editFolder(@ModelAttribute("folder") Folder folder){
        folderService.save(folder);
        return "redirect:/folders";
    }

    @PreAuthorize("@folderService.getById(#id).idUserFK.login == authentication.name")
    @GetMapping("/{id}") //удаление набора слов
    public String deleteFolderWarning(@PathVariable Long id, Model model) {
        model.addAttribute("folder", folderService.getById(id));
        return "/folder/deleteFolder";
    }

    @DeleteMapping("/{id}") //удаление набора слов
    public String deleteFolder(@PathVariable Long id){
        folderService.delete(id);
        return "redirect:/folders";
    }

}
