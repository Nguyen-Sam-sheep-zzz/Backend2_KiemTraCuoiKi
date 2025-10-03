package com.codegym.springbootjwtdemo.service;

import com.codegym.springbootjwtdemo.model.Country;

import java.util.List;

public interface ICountryService {
    List<Country> findAll();

    Country findById(Long id);

    Country save(Country country);

    void delete(Long id);
}
