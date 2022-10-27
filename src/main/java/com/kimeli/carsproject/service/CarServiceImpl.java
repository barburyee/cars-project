package com.kimeli.carsproject.service;

import com.kimeli.carsproject.model.Car;
import com.kimeli.carsproject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepository carRepository;
    @Override
    public void saveCarDetails(Car car) {
        carRepository.save(car);
    }

    @Override
    public Object findAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Object> findCarById(Long id) {
        return (List<Object>) carRepository.findById(id).get();
    }
}
