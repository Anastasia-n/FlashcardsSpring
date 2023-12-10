package ru.anastasia.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Vocabulary;

import java.util.List;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Long> {
    List<Vocabulary> findAllByIdFolderFK (Folder folder);
    Page<Vocabulary> findAllByIdFolderFK (Folder folder, Pageable pageable);
}
