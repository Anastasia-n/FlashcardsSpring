package ru.anastasia.spring.service;

import jakarta.servlet.http.HttpSession;
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

    public boolean updateUser (Users user, HttpSession session) {
        if(user.getLogin().equals(((Users) session.getAttribute("userInfo")).getLogin()) || usersRepository.findById(user.getId()).isEmpty()){
            user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
            usersRepository.saveAndFlush(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean saveUser(Users users){
        if(usersRepository.findByLogin(users.getLogin()).isPresent()){
            return false;
        }
        users.setRole(Role.USER);
        users.setPassword(new BCryptPasswordEncoder(12).encode(users.getPassword()));
        usersRepository.save(users);
        return true;
    }
}
