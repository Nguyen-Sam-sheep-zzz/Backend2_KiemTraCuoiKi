package com.codegym.springbootjwtdemo.repository;

import com.codegym.springbootjwtdemo.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {

}
