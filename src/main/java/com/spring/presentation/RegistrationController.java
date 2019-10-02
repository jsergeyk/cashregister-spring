package com.spring.presentation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    
	//private ApplicationContext applicationContext;
	  
    @GetMapping("/registration")
    public String findById( Model model) {
        return "registration";
    }
}
