package ru.anastasia.spring.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.anastasia.spring.models.Role;
import ru.anastasia.spring.models.Users;
import ru.anastasia.spring.repository.UsersRepository;

@Service
@Transactional
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users getByLogin (String login){
        return usersRepository.findByLogin(login).get();
    }

    public void setNewName (Users user) {
        usersRepository.saveAndFlush(user);
    }

    public boolean addNewUser(Users users){
        if(usersRepository.findByLogin(users.getLogin()).isPresent()){
            return false;
        }
        users.setRole(Role.USER);
        users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
        usersRepository.save(users);
        return true;
    }
}
