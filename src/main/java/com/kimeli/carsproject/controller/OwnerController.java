package com.kimeli.carsproject.controller;

import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.UsersRepository;
import com.kimeli.carsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OwnerController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsersRepository userRepo;
    //Load Interface To enter Car Owner Details
    @GetMapping("/addOwner")
    public String showCarOwners(Model model){
        Users user = new Users();
        model.addAttribute("user", user);
        return "add_owner";
    }
    //Handles Form Submission to save Car Owner Details Submitted
    @PostMapping("/saveOwner")
    public String showOwnersForm(@ModelAttribute Users users){
        userService.saveUser(users);
        return "redirect:ownersList";
    }

    @GetMapping("/ownersList")
    public String carOwnersList(Model model)
    {
        model.addAttribute("users", userService.findAllUsers());
        System.out.println("Users Details: "+userService.findAllUsers().toString());
        return "owners_list";
    }

    @GetMapping("/viewOwner/{id}")
    public String getSpecificOwner(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userRepo.findById(id));
        return "view_owner";
    }

}
