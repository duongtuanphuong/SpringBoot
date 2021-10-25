package com.example.upload.controller;

import com.example.upload.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public String getHome(Model model){
        model.addAttribute("images", imageService.getAll());
        return "home";
    }
}
