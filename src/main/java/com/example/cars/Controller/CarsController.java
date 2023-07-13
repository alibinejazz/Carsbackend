package com.example.cars.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cars.Model.Cars;
import com.example.cars.Repository.CarsRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private CarsRepository carrepo;

    @PostMapping("/add")
    public String addCars(@RequestBody Cars car){
        carrepo.save(car);
        return "Cars Added";
    }

    @GetMapping("/get/{id}")
    public Cars getACar(@PathVariable Long id){
        
       return carrepo.findById(id).orElse(null);
        
    }

    @GetMapping("/getall")
    public List<Cars> allHotels(){
        return carrepo.findAll();
    }
}
