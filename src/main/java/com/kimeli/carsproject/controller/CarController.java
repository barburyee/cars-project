package com.kimeli.carsproject.controller;

import com.kimeli.carsproject.model.Car;
import com.kimeli.carsproject.model.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class CarController {
    //To load Interface with form for adding car details to the system
    @GetMapping("/addCar")
    public String showRegistrationForm(Model model){
        Car car = new Car();
        model.addAttribute("car",car);
        List<String> ownerList = Arrays.asList("23344","23723","7323","62133");
        model.addAttribute("ownerList",ownerList);
        return "add_car";
    }
    //To add the car details and there after load the interface with details of car added to system
    @PostMapping("/addCar")
    public String submitFormNow(@ModelAttribute("car")Car car){
        System.out.println(car);
        return "add_car_success";
    }
    //To load View with form to add car owners to the system
    @GetMapping("/addCarOwner")
    public String showOwnersForm(Model model){
        Users users = new Users();
        model.addAttribute("users",users);
        return "add_owner";
    }
}
