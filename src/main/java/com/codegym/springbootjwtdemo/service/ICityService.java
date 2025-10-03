package com.codegym.springbootjwtdemo.service;

import com.codegym.springbootjwtdemo.model.City;

import java.util.List;

public interface ICityService {
    List<City> findAll();

    City findById(Long id);

    City save(City city);

    void delete(Long id);
}
