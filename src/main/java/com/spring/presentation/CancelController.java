package com.spring.presentation;

import lombok.AllArgsConstructor;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.spring.persistence.entity.*;
import com.spring.service.CheckService;
import com.spring.service.Report;
import com.spring.service.ReportService;

@Controller
@AllArgsConstructor
public class CancelController {
	
	private final CheckService checkService;
	private final ReportService reportService;
	  
    @GetMapping("/cancel")
    public String cancelView() {
        return "/cancel";
    }
    
    @PostMapping(value = "/cancel", params = "btnSearchCheck")
    public String searchCheck(Model model, HttpSession session,
    		@RequestParam("checkid") Long checkid) {
    	Optional<Chec> check = checkService.findById(checkid);
		if (check.isPresent()) {
			session.setAttribute("check", check.get());
			session.setAttribute("checkspecs", check.get().getCheckspecCollection());
			session.setAttribute("checkfind", null);
		} else {
			session.setAttribute("check", null);
			session.setAttribute("checkfind", checkid);
		}
    	return "/cancel";
    }
    
    /**
     * Отменить выбранную спецификацию чека 
     * @param session сессия
     * @param count № п/п спецификации чека 
     * @return ModelAndView
     */
    @GetMapping("/cancel/edit/{count}")
    public ModelAndView cancelSpec(HttpSession session, @PathVariable Integer count) {
    	@SuppressWarnings("unchecked")
    	Collection<Checkspec> checkspecs = (Collection<Checkspec>) session.getAttribute("checkspecs");
    	checkService.cancelCheckSpec((List<Checkspec>)checkspecs, count);
        return new ModelAndView("redirect:/cancel");
    }    
    
    /**
     * Отменить чек 
     * @param session сессия
     * @return
     */
    @PostMapping(value = "/cancel", params = "btnCancelCheck")
    public String cancelCheck(HttpSession session) {    	
		Chec check = (Chec)session.getAttribute("check");
		checkService.cancelCheckSpec(check);
    	return "/cancel";
    }
    
    /**
     * Сформировать Х-отчет 
     * @param session сессия
     * @return
     */
    @PostMapping(value = "/cancel", params = "btnXReport")
    public ModelAndView printXReport(HttpSession session) {
		Report xReport = reportService.getDataXReport();
		session.setAttribute("xReport", xReport);
		session.setAttribute("zReport", null);
    	return new ModelAndView("redirect:/report");
    }
}
