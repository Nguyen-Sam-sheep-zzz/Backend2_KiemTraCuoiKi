package com.codegym.springbootjwtdemo.controller;

import com.codegym.springbootjwtdemo.model.City;
import com.codegym.springbootjwtdemo.model.Country;
import com.codegym.springbootjwtdemo.service.ICityService;
import com.codegym.springbootjwtdemo.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cities")
public class CityViewController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    // ---------------- Danh sách ----------------
    @GetMapping
    public String listCities(Model model) {
        model.addAttribute("cities", cityService.findAll());
        return "cities"; // Thymeleaf template cities.html
    }

    // ---------------- Thêm mới ----------------
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryService.findAll());
        return "city_add";
    }

    @PostMapping("/add")
    public String addCity(@ModelAttribute City city) {
        cityService.save(city);
        return "redirect:/cities";
    }

    // ---------------- Sửa ----------------
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        model.addAttribute("countries", countryService.findAll());
        return "city_edit";
    }


    @PostMapping("/edit/{id}")
    public String editCity(@PathVariable Long id, @ModelAttribute City city) {
        Country country = countryService.findById(city.getCountry().getId());
        city.setCountry(country);

        city.setId(id);
        cityService.save(city);
        return "redirect:/cities";
    }


    // ---------------- Xóa ----------------
    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Long id) {
        cityService.delete(id);
        return "redirect:/cities";
    }

    // ---------------- Chi tiết ----------------
    @GetMapping("/{id}")
    public String cityDetails(@PathVariable Long id, Model model) {
        City city = cityService.findById(id);
        model.addAttribute("city", city);
        return "city_detail";
    }
}
