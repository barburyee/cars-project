package com.kimeli.carsproject.service;

import com.kimeli.carsproject.model.Users;

import java.util.List;

public interface UserService {
    public List<Users> findAllUsers();

    public void saveUser(Users users);

    public List<Users> findByFirstName(String fName);
}
