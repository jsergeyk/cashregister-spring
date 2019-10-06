package com.spring.presentation;

import com.spring.persistence.entity.User;
import com.spring.service.UserService;

import lombok.AllArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/")
@AllArgsConstructor
public class UserController {
    
	private final UserService userService;
	
    @GetMapping("/")
    public String getSignIn() {
        return "/index";
    }
    
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request/*, HttpServletResponse response*/) {
    	HttpSession session = request.getSession(false);
        if (session != null) {
        	session.invalidate();
        }
        return new ModelAndView("redirect:/");
    }
    
    @PostMapping("/")
    public ModelAndView postSignIn(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpSession session,
                             Model model) {
        User user = userService.findByLoginAndPassword(email,  password);
        if (user != null) {
        	session.setAttribute("user", user);
        	session.setAttribute("userNotExists", null);
            String userType = user.getUserType().getType();
            if (userType.equalsIgnoreCase("goods_spec")) {				//товаровед
            	return new ModelAndView("redirect:/goods");
    		} else if (userType.equalsIgnoreCase("cashier")) {			//кассир
    			return new ModelAndView("redirect:/check");
    		} else if (userType.equalsIgnoreCase("senior_cashier")) {	//старший кассир
    			return new ModelAndView("redirect:/cancel");
    		}
        } else {
        	session.setAttribute("user", null);
			session.setAttribute("userNotExists", true);			
		}
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/");
    }   
    
  /*    
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("/templates/");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }*/
}
