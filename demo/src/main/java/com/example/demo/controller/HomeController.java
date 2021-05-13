package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserService userService; 
    
    @GetMapping("/")
    public String getHome(Model model,@RequestParam(required = false) Integer page){
        if(page == null){
            page =0;
        }
        else{
            page --;
            if(page <0) {
                page = 0;
            }
        }
        Page<User> user = userService.getAllUser(page);
        model.addAttribute("users", user);

        return "home";
    }


    @GetMapping("/admin")
    public String getAdmin(){
        return "/admin/product/create";
    }
}
