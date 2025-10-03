package com.codegym.springbootjwtdemo.controller;

import com.codegym.springbootjwtdemo.model.User;
import com.codegym.springbootjwtdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserViewController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("listUsers", userService.findAll().stream()
                .filter(u -> keyword == null || u.getName().toLowerCase().contains(keyword.toLowerCase()))
                .toList());
        model.addAttribute("keyword", keyword);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        User u = userService.findById(id) != null ?
                new User(userService.findById(id).getId(), userService.findById(id).getName(), null, null)
                : null;
        model.addAttribute("user", u);
        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
