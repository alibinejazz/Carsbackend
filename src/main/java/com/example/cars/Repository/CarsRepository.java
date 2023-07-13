package com.example.cars.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cars.Model.Cars;

public interface CarsRepository extends JpaRepository<Cars,Long> {
    
}
