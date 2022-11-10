package com.kimeli.carsproject.service;

import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void saveUser(Users users) {
        usersRepository.save(users);
    }

    @Override
    public List<Users> findByFirstName(String fName) {
        return usersRepository.findByFirstName(fName);
    }
}
