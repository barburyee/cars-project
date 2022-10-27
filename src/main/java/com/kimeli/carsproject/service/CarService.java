package com.kimeli.carsproject.service;

import com.kimeli.carsproject.model.Car;

import java.util.List;

public interface CarService {

    public void saveCarDetails(Car car);

    public Object findAllCars();

   public List<Object> findCarById(Long id);
}
