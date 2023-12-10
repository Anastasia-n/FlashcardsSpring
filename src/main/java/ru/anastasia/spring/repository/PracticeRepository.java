package ru.anastasia.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Practice;

@Repository
public interface PracticeRepository extends JpaRepository<Practice, Long> {
    Boolean existsPracticeByIdFolderFK (Folder folder);
    Practice getPracticeByIdFolderFK(Folder folder);
}
