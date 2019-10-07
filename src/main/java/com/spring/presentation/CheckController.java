package com.spring.presentation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.spring.persistence.entity.Checkspec;
import com.spring.persistence.entity.User;
import com.spring.service.CheckService;


@Controller
@Slf4j
@AllArgsConstructor
public class CheckController {
    
    private final CheckService checkService;
    
    @GetMapping("/check")
    public String viewCheck() {
        return "/check";
    }
    
    /**
     * Добавить спецификацию(позицию) чека 
     * @param model
     * @param session
     * @param code
     * @param name
     * @param quant
     * @param nds
     * @return
     */
    @PostMapping(value = "/check", params = "btnAddCheckspec")
    public String addCheckSpec(Model model, HttpSession session,
    		@RequestParam("code") Integer code, @RequestParam("name") String name,
    		@RequestParam("quant") Double quant, @RequestParam("nds") Integer nds) {
		@SuppressWarnings("unchecked")
		List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
		if (checkspecs == null) {
			checkspecs = new ArrayList<>();
			session.setAttribute("addcheckspecs", checkspecs);
		}
		Checkspec spec = checkService.addCheckSpec(code, name, quant, nds);
		if (spec != null) {
			checkspecs.add(spec);
		} else {
			if (code != null) {
				model.addAttribute("goodsCodeNotFound", code);
			} else {
				model.addAttribute("goodsNameNotFound", name);
			}
		}
		return "/check";
    }
    
	/**
	 * Добавить чек со спецификациями (в транзакции)
	 */
    @PostMapping(value = "/check", params = "btnCreateCheck") 
    public String createCheck(HttpSession session, HttpServletRequest request) {
    	@SuppressWarnings("unchecked")
		List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
    	if (checkspecs != null && checkspecs.size() > 0) {
			try {
				checkService.addCheck((User) session.getAttribute("user"), checkspecs);
				request.setAttribute("addedCheck", true);
				checkspecs.clear();
			} catch (/*Transaction*/Exception e) {
				request.setAttribute("addedCheck", false);
				checkspecs.clear();
				log.error("Ошибка транзакции при добавлении чека и спецификаций. ", e);
			}
    	}
    	return "/check";    	
    }
    
    /**
     * Очистить спецификации чека
     */
    @PostMapping(value = "/check", params = "btnCancelCheck") 
    public String clearCheck(HttpSession session) {
    	@SuppressWarnings("unchecked")
		List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
    	if (checkspecs != null) {
    		checkspecs.clear();
    	}
    	return "/check";
    }
    
    /**
     * Удалить выбранную спецификацию чека
     * @param session сессия
     * @param count № п/п спецификация чека
     * @return
     */
    @GetMapping("/check/del/{count}")
    public ModelAndView editGoods(HttpSession session, @PathVariable Integer count) {
    	@SuppressWarnings("unchecked")
		List<Checkspec> checkspecs = (List<Checkspec>) session.getAttribute("addcheckspecs");
    	if (checkspecs.size() >= count && count > 0) {
    		checkspecs.remove(count - 1);
    	}
        return new ModelAndView("redirect:/check");
    }
}
