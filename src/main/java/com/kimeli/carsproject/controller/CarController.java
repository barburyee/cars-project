package com.kimeli.carsproject.controller;

import com.kimeli.carsproject.model.Car;
import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.CarRepository;
import com.kimeli.carsproject.repository.UsersRepository;
import com.kimeli.carsproject.service.CarService;
import com.kimeli.carsproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    @Autowired
    private CarRepository carRepository;
    //To load Interface with form for adding car details to the system
    @GetMapping("/addCar")
    public String showRegistrationForm(Model model){
        Car car = new Car();
        model.addAttribute("car",car);
        List<String> ownerList = Arrays.asList("23344","23723","7323","62133");
        model.addAttribute("ownerList",ownerList);
        List<Users> test = new ArrayList<>();
        model.addAttribute("user", test);
        List<Users> tests = userService.findAllUsers();
        System.out.println("All Cars: "+ tests);
        model.addAttribute("users", tests);
        return "add_car";
    }
    //To add the car details and there after load the interface with details of all cars added to system
    @PostMapping("/addCar")
    public String submitForm(@RequestParam("carreg") String carreg,
                             @RequestParam("carmade") String carmade,
                             @RequestParam("carmodel") String carmodel,
                             @RequestParam("capturepicturepath") MultipartFile capturepicturepath,
                             @RequestParam("carownerid") Integer carownerid
                             ){
        Car car = new Car();
        car.setCarreg(carreg);
        car.setCarmade(carmade);
        car.setCarmodel(carmodel);
        car.setCarownerid(carownerid);
        if(capturepicturepath.isEmpty()){
            return "redirect:addCar";
        }
        Path path = Paths.get("images/");
        try{
            InputStream inputStream = capturepicturepath.getInputStream();
            Files.copy(inputStream, path.resolve(capturepicturepath.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
                    );
            car.setCapturepicturepath(capturepicturepath.getOriginalFilename().toLowerCase());
            carService.saveCarDetails(car);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "redirect:carsList";
    }
    //To load View with form to add car owners to the system

    @GetMapping("/carsList")
    public String carsList(Model model)
    {
        model.addAttribute("cars", carService.findAllCars());
        return "index";
    }
    @GetMapping("/")
    public String carsListed(Model model)
    {
        model.addAttribute("cars", carService.findAllCars());
        return "index";
    }
    @GetMapping("/viewSpecificCar/{id}")
    public String getSpecificCar(@PathVariable("id") Long id, Model model){
        System.out.println("Car Details: "+carRepository.findById(id));
        model.addAttribute("car", carRepository.findById(id));
        System.out.println("Car Photo URL: "+carRepository.findById(id).get().getCapturepicturepath());
        model.addAttribute("cars", carRepository.findById(id).get().getCapturepicturepath());
        System.out.println("Current Details: "+carRepository.findById(id));
        return "view_car";
    }
    //To return the uploade Image when feed with image file name
    @GetMapping("/getImage/{photo}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo ){
        if(!photo.equals("")||photo!=null){
            try{
                Path filename = Paths.get("images",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length).contentType(MediaType.parseMediaType("image/png")).
                        body(byteArrayResource);
            }catch (Exception ex){

            }
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/viewSpecificCar/getImage/{photo}")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImageNow(@PathVariable("photo") String photo ){
        if(!photo.equals("")||photo!=null){
            try{
                Path filename = Paths.get("images",photo);
                byte[] buffer = Files.readAllBytes(filename);
                ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
                return ResponseEntity.ok().contentLength(buffer.length).contentType(MediaType.parseMediaType("image/png")).
                        body(byteArrayResource);
            }catch (Exception ex){

            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/testOwnersList")
    public ResponseEntity<List<Users>> testRegistrationForm(@RequestParam(required = false) String name){

        List<String> professionList = Arrays.asList("Developer","Designer","Architect","DevOps");

        if(name == null){
            return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
        }

        /*List<Users> usersList = new ArrayList<>();
        Users users = userService.findByFirstName(name);
        if(users!=null){
            usersList.add(users);
        }
        if(users.isEmpty()){
            //throw new UsersNotFoundException("Not");
        }*/
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);


    }

}
