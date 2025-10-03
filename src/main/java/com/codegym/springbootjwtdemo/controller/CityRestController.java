package com.codegym.springbootjwtdemo.controller;

import com.codegym.springbootjwtdemo.model.City;
import com.codegym.springbootjwtdemo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityRestController {

    @Autowired
    private ICityService cityService;

    // ---------------- GET ALL -------------------
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // ---------------- GET BY ID -------------------
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCityById(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            return new ResponseEntity<>(city, HttpStatus.OK);
        }
        return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
    }

    // ---------------- CREATE -------------------
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // chỉ admin mới tạo
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City savedCity = cityService.save(city);
        return new ResponseEntity<>(savedCity, HttpStatus.CREATED);
    }

    // ---------------- UPDATE -------------------
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // chỉ admin mới sửa
    public ResponseEntity<Object> updateCity(@PathVariable Long id, @RequestBody City city) {
        City existingCity = cityService.findById(id);
        if (existingCity != null) {
            city.setId(id);
            City updatedCity = cityService.save(city);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        }
        return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
    }

    // ---------------- DELETE -------------------
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCity(@PathVariable Long id) {
        City city = cityService.findById(id);
        if (city != null) {
            cityService.delete(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("City not found", HttpStatus.NOT_FOUND);
    }
}
