package com.spring.presentation;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.service.CheckService;

@Controller
//@RequestMapping("/check")
@AllArgsConstructor
public class CheckController {
    
    private final CheckService checkService;
    
    @GetMapping	//укороченная @RequestMapping(method = RequestMethod.GET).
    public String createChecks() {
        //ModelAndView modelAndView = new ModelAndView("check");
        //modelAndView.addObject("page", checkService.findAll(page));
 
        return "/check";
    }
}
