package ru.anastasia.spring.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Practice;
import ru.anastasia.spring.models.Vocabulary;
import ru.anastasia.spring.service.*;

import java.time.Duration;
import java.util.Calendar;

@Controller
@RequestMapping("/learning")
public class LearningController {

    final VocabularyService vocabularyService;
    final FolderService folderService;
    final SpacedRepetitionService spacedRepetitionService;
    final PracticeService practiceService;
    final ProgressService progressService;

    private int correctAnswers = 0;
    private int mistakes = 0;
    private int currentPage = 0;

    public LearningController(VocabularyService vocabularyService, FolderService folderService, SpacedRepetitionService spacedRepetitionService, PracticeService practiceService, ProgressService progressService) {
        this.vocabularyService = vocabularyService;
        this.folderService = folderService;
        this.spacedRepetitionService = spacedRepetitionService;
        this.practiceService = practiceService;
        this.progressService = progressService;
    }

    @GetMapping("/{id}")
    public String learnVocabulary(@PathVariable Long id, @PageableDefault(size=1) Pageable pageable, Model model){
        Folder folder = folderService.getById(id);
        Page<Vocabulary> page = vocabularyService.getVocabularyForPagination(folder, pageable);
        model.addAttribute("page",page);
        model.addAttribute("back",folder.getId());
        model.addAttribute("counter",correctAnswers);
        model.addAttribute("currentPage",currentPage);
        return "/learning/learningPage";
    }

    @GetMapping("/check")
    public ModelAndView checkWord(@RequestParam("wordToCompare") String wordToCompare,
                                  @RequestParam("wordToCheck") String wordToCheck,
                                  @RequestParam("pageNumber") String pageNumber,
                                  @RequestParam("back") Long folderId) {
        if (wordToCompare.equals(wordToCheck))
            correctAnswers++;
        else
            mistakes++;
        currentPage++;
        return new ModelAndView("redirect:/learning/" + folderId + "?page=" + pageNumber +"&size=1");
    }

    @GetMapping("/interrupt")
    public String interruptLearning(@RequestParam("id") Long folderId){
        clearCounters();
        return "redirect:/vocabulary?folder=" + folderId;
    }

    @GetMapping("/finish")
    public String finishLearning(@RequestParam("numberOfPages") String numberOfPages,
                                 @RequestParam("back") Long folderId,
                                 Model model){
        model.addAttribute("correct", correctAnswers);
        model.addAttribute("mistakes", mistakes);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("back", folderId);
        return "/learning/learningResult";
    }

    private void clearCounters() {
        correctAnswers = 0;
        mistakes = 0;
        currentPage = 0;
    }

    @GetMapping("/finish/delete")
    public String finishAndDelete(@RequestParam("back") Long folderId){
        clearCounters();
        return "redirect:/vocabulary?folder=" + folderId;
    }

    @PostMapping("/finish/save")
    public String finishAndSave(@RequestParam("back") Long folderId){
        Folder folder = folderService.getById(folderId);
        Practice practice = new Practice();
        if(practiceService.checkIfExists(folder)){
            practice = practiceService.getPractice(folder);
            practice.setNumberOfPractices(practice.getNumberOfPractices()+1);
            if(practice.getRepetitionStage() <= 10
                    && spacedRepetitionService.getTimeLeft(practice).isNegative()) {
                practice.setRepetitionStage(practice.getRepetitionStage()+1);
                practice.setFirstPracticeDate(Calendar.getInstance());
            }
            practice.setLastPracticeDate(Calendar.getInstance());
        }
        else {
            practice.setNumberOfPractices(1);
            practice.setFirstPracticeDate(Calendar.getInstance());
            practice.setLastPracticeDate(Calendar.getInstance());
            practice.setRepetitionStage(1);
            practice.setIdFolderFK(folder);
        }
        practiceService.add(practice);
        progressService.add(correctAnswers, mistakes, practice);
        clearCounters();
        return "redirect:/vocabulary?folder=" + folderId;
    }

    @GetMapping("/reset")
    public String counterReset(@RequestParam("folder")Long id, Model model){
        model.addAttribute("folder",folderService.getById(id));
        return "/learning/learningReset";
    }

    @DeleteMapping("/reset") // начать изучение заново (сброс отсчета)
    public String counterResetting (@ModelAttribute("folder") Folder folder) {
        if(practiceService.checkIfExists(folder)) {
            Practice practice = practiceService.getPractice(folder);
            practiceService.delete(practice);
        }
        return "redirect:/vocabulary?folder=" + folder.getId();
    }

    @GetMapping("/statistics") // показать статистику модуля
    public String showStatistics (@RequestParam("folder") Long id, Model model) {
        Folder folder = folderService.getById(id);
        int number = 0;
        String lastLearning = "нет данных";
        double result = 0;
        if(practiceService.checkIfExists(folder)) {
            Practice practice = practiceService.getPractice(folder);
            if (practice.getLastPracticeDate() != null) {
                number = practice.getNumberOfPractices();
                Duration duration = Duration.between(practice.getLastPracticeDate().toInstant(), Calendar.getInstance().toInstant());
                lastLearning = duration.toDaysPart() + " дн. " + duration.toHoursPart() + " ч. " +
                        duration.toMinutesPart() + " мин. " + duration.toSecondsPart() + " c. назад";
                result = progressService.getAverageResult(practice);
            }
        }
        model.addAttribute("number",number);
        model.addAttribute("lastLearning", lastLearning);
        model.addAttribute("result", result);
        model.addAttribute("folder", folder);
        return "/learning/learningStatistics";
    }

    @PostMapping("/statistics/delete") //сброс статистики
    public String deleteStatistics (@ModelAttribute("folder") Folder folder) {
        if(practiceService.checkIfExists(folder)) {
            Practice practice = practiceService.getPractice(folder);
            practice.setNumberOfPractices(0);
            practice.setLastPracticeDate(null);
            progressService.deleteResults(practice);
        }
        return "redirect:/vocabulary?folder=" + folder.getId();
    }

}
