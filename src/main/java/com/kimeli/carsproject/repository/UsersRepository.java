package com.kimeli.carsproject.repository;

import com.kimeli.carsproject.model.Car;
import com.kimeli.carsproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByFirstName(String fName);
//    List<Integer> findByIdnumber();
    //List<Users> findByNumber(String idnumber);
}
