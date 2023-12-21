package ru.anastasia.spring.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Vocabulary;
import ru.anastasia.spring.repository.VocabularyRepository;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class VocabularyService {
    final
    VocabularyRepository vocabularyRepository;

    public VocabularyService(VocabularyRepository vocabularyRepository) {
        this.vocabularyRepository = vocabularyRepository;
    }

//    public List<Vocabulary> getAll(Folder folder){
//        return vocabularyRepository.findAllByIdFolderFK(folder);
//    }

    public void save (Vocabulary vocabulary){
        vocabularyRepository.saveAndFlush(vocabulary);
    }

    public Vocabulary getById (Long id){
        return vocabularyRepository.findById(id).get();
    }

    public void delete (Long id){
        vocabularyRepository.deleteById(id);
    }

    public Page<Vocabulary> getVocabularyForPagination(Folder folder, Pageable pageable) {
        return vocabularyRepository.findAllByIdFolderFK(folder, pageable);
    }

}
