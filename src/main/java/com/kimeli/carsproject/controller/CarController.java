package com.kimeli.carsproject.controller;

import com.kimeli.carsproject.model.Car;
import com.kimeli.carsproject.model.Users;
import com.kimeli.carsproject.repository.CarRepository;
import com.kimeli.carsproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Controller
public class CarController {
    @Autowired
    private CarRepository carRepo;

    @Autowired
    private UsersRepository usersRepository;
    //To load Interface with form for adding car details to the system
    @GetMapping("/addCar")
    public String showRegistrationForm(Model model){
        Car car = new Car();
        model.addAttribute("car",car);
        List<String> ownerList = Arrays.asList("23344","23723","7323","62133");
        model.addAttribute("ownerList",ownerList);
        List<Users> test = new ArrayList<>();
        model.addAttribute("user", test);
        List<Users> tests = usersRepository.findAll();
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
            carRepo.save(car);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "redirect:carsList";
    }
    //To load View with form to add car owners to the system

    @GetMapping("/carsList")
    public String carsList(Model model)
    {
        model.addAttribute("cars", carRepo.findAll());
        return "index";
    }
    @GetMapping("/")
    public String carsListed(Model model)
    {
        model.addAttribute("cars", carRepo.findAll());
        return "index";
    }
    @GetMapping("/viewSpecificCar/{id}")
    public String getSpecificCar(@PathVariable("id") Long id, Model model){
        System.out.println("Car Details: "+carRepo.findById(id));
        model.addAttribute("car", carRepo.findById(id));
        System.out.println("Car Photo URL: "+carRepo.findById(id).get().getCapturepicturepath());
        model.addAttribute("cars", carRepo.findById(id).get().getCapturepicturepath());
        System.out.println("Current Details: "+carRepo.findById(id));
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

}
