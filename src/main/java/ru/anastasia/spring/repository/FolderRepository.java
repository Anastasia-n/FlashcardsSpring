package ru.anastasia.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Users;

import java.util.List;
import java.util.Optional;


@Repository
public interface FolderRepository extends JpaRepository<Folder,Long> {
    List<Folder> findAllByIdUserFK(Users users);

}
