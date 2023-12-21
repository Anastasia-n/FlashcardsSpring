package ru.anastasia.spring.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Folder;
import ru.anastasia.spring.models.Users;
import ru.anastasia.spring.repository.FolderRepository;

import java.util.List;

@Service
@Transactional
public class FolderService {

    final
    FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

//    public List<Folder> getAllById(Users users){
//        return folderRepository.findAllByIdUserFK(users);
//    }

    public void save (Folder folder){
        folderRepository.saveAndFlush(folder);
    }

    public Folder getById (Long id){
        return folderRepository.findById(id).get();
    }

    public void delete (Long id){
        folderRepository.deleteById(id);
    }

    public Folder getReferenceById (Long id){
        return folderRepository.getReferenceById(id);
    }

}
