package com.spring.presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * @author SergeyK
 */
@Controller
@Slf4j
public class MyErrorController implements ErrorController {

	@Override
	public String getErrorPath() {
		return  "/error";
	}
	
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
    	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    	model.addAttribute("error_status", status);
    	log.error("ERROR_STATUS_CODE:" + status);
        return "error";
    }
}
