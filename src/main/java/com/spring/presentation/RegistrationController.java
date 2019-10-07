package com.spring.presentation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.persistence.entity.User;
import com.spring.service.UserService;

@Controller
@Slf4j
@AllArgsConstructor
public class RegistrationController {
	
	private final UserService userService;
	//private ApplicationContext applicationContext;
	  
    @GetMapping("/registration")
    public String registrationView(Model model) {
    	model.addAttribute("user", new User());
        return "/registration";
    }
    
    @PostMapping("/registration")
    public ModelAndView registration(HttpSession session, Model model, @ModelAttribute User user) {
		User newUser = userService.registration(user);
		if(newUser != null) {
			newUser.setPassword(null);
			session.setAttribute("user", newUser);
			log.info("Регистрация нового пользователя " + newUser.getName());
			return new ModelAndView("redirect:/check");
		} else {
			log.info("Регистрация не удалась. Пользователь с таким email уже существует");
			model.addAttribute("existsLogin",  user.getLogin());
			return new ModelAndView("/registration");
		}
    }
}
