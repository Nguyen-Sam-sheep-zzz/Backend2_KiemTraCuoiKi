package com.codegym.springbootjwtdemo.repository;

import com.codegym.springbootjwtdemo.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

}
