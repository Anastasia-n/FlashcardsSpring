package ru.anastasia.spring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Vocabulary;
import ru.anastasia.spring.service.FolderService;
import ru.anastasia.spring.service.SpacedRepetitionService;
import ru.anastasia.spring.service.VocabularyService;

@Controller
@RequestMapping("/vocabulary")
public class VocabularyController {

    final VocabularyService vocabularyService;
    final FolderService folderService;
    final SpacedRepetitionService spacedRepetitionService;

    public VocabularyController(VocabularyService vocabularyService, FolderService folderService, SpacedRepetitionService spacedRepetitionService) {
        this.vocabularyService = vocabularyService;
        this.folderService = folderService;
        this.spacedRepetitionService = spacedRepetitionService;
    }

    @PreAuthorize("@folderService.getById(#id).idUserFK.login == authentication.name")
    @GetMapping() //Показать все слова модуля
    public String getWords(@RequestParam("folder") Long id, Model model){
        Folder folder = folderService.getById(id);
        model.addAttribute("showFolder", folder);
        model.addAttribute("words",vocabularyService.getAll(folder));
        model.addAttribute("timeLeft", spacedRepetitionService.showTimeLeft(folder));
        return "/vocabulary/showVocabulary";
    }

    @PreAuthorize("@folderService.getById(#id).idUserFK.login == authentication.name")
    @GetMapping("/new") //Создание нового слова
    public String addWord(@RequestParam("id") Long id, @ModelAttribute("vocabulary") Vocabulary vocabulary){
        vocabulary.setIdFolderFK(folderService.getById(id));
        return "/vocabulary/addWord";
    }

    @PostMapping() //Создание нового слова
    public String creatingWord(@ModelAttribute("vocabulary") Vocabulary vocabulary){
        vocabularyService.save(vocabulary);
        return "redirect:/vocabulary?folder=" + vocabulary.getIdFolderFK().getId();
    }

    @PreAuthorize("@vocabularyService.getById(#id).idFolderFK.idUserFK.login == authentication.name")
    @GetMapping("{id}/edit") //Редактирование слова
    public String editWordForm(@PathVariable Long id, Model model){
        model.addAttribute("wordToEdit", vocabularyService.getById(id));
        return "/vocabulary/editWord";
    }

    @PatchMapping("/{id}") //Редактирование слова
    public String editWord(@ModelAttribute("wordToEdit") Vocabulary vocabulary){
        vocabularyService.save(vocabulary);
        return "redirect:/vocabulary?folder=" + vocabulary.getIdFolderFK().getId();
    }

    @PreAuthorize("@vocabularyService.getById(#id).idFolderFK.idUserFK.login == authentication.name")
    @GetMapping("/{id}") //Удаление слова
    public String deleteWordWarning(@PathVariable Long id, Model model) {
        model.addAttribute("wordToDelete", vocabularyService.getById(id));
        return "/vocabulary/deleteWord";
    }

    @DeleteMapping("/{id}") //Удаление слова
    public String deleteWord(@PathVariable Long id) {
        Long back = vocabularyService.getById(id).getIdFolderFK().getId();
        vocabularyService.delete(id);
        return "redirect:/vocabulary?folder=" + back;
    }
}
