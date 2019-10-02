package com.spring.presentation;

import com.spring.persistence.entity.Goods;
import com.spring.service.GoodsService;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/")
@AllArgsConstructor
public class GoodsController {
    
	private final GoodsService goodsService;
	
//	private ApplicationContext applicationContext;	
	
    @GetMapping("/goods")
    public String viewGoods(Model model) {
    	int page = 0;
        int recordsPerPage = 10;
		Page<Goods> goods = goodsService.view(page, recordsPerPage);
		model.addAttribute("viewGoods", goods);
        return "/goods";
    }
    
    @PostMapping("/goods")
    public String postSignIn(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
    	
    	 return "goods";
    }
}
