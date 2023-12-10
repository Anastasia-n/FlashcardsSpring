package ru.anastasia.spring.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Practice;
import ru.anastasia.spring.models.Progress;
import ru.anastasia.spring.repository.ProgressRepository;

@Service
@Transactional
public class ProgressService {

    final
    ProgressRepository progressRepository;

    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    public void add (int correct, int mistakes, Practice practice){
        Progress progress = new Progress();
        progress.setCorrect(correct);
        progress.setMistakes(mistakes);
        progress.setPractice(practice);
        progressRepository.saveAndFlush(progress);
    }

    public double getAverageResult(Practice practice){
        return progressRepository.getResult(practice.getId().intValue());
    }

    public void deleteResults(Practice practice){
        progressRepository.deleteAllByIdPracticeFK(practice);
    }
}
