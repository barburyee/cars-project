package com.kimeli.carsproject.controller;

import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnerController {
    @Autowired
    UsersRepository userRepo;

    @GetMapping("/addOwner")
    public String showCarOwners(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "add_owner";
    }


    @RequestMapping("/ownersList")
    public String carOwnersList(Model model)
    {
        model.addAttribute("users", userRepo.findAll());
        return "owners_list";
    }
}
