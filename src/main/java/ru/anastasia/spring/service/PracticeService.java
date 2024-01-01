package ru.anastasia.spring.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Practice;
import ru.anastasia.spring.repository.PracticeRepository;

import java.util.Calendar;

@Service
@Transactional
public class PracticeService {

    final
    PracticeRepository practiceRepository;

    public PracticeService(PracticeRepository practiceRepository) {
        this.practiceRepository = practiceRepository;
    }

    public void reset(Folder folder){
        if(checkIfExists(folder)) {
            delete(getPractice(folder));
        }
    }

    public Boolean checkIfExists(Folder folder){
        return practiceRepository.existsPracticeByIdFolderFK(folder);
    }

    public Practice getPractice(Folder folder){
        return practiceRepository.getPracticeByIdFolderFK(folder);
    }

    public void add(Practice practice){
        practiceRepository.saveAndFlush(practice);
    }

    public void delete(Practice practice){
        practiceRepository.delete(practice);
    }
}
