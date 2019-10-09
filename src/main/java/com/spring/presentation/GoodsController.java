package com.spring.presentation;

import com.spring.persistence.entity.Goods;
import com.spring.service.GoodsService;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/")
@AllArgsConstructor
public class GoodsController {
    
	private final GoodsService goodsService;	
//	private ApplicationContext applicationContext;	
	
    @GetMapping("/goods")
    public String viewGoods(Model model, @RequestParam("page") Optional<Integer> page, 
    	      @RequestParam("size") Optional<Integer> size) {
        addPagination(model, page, size);
        return "/goods";
    }
    
	@PostMapping("/goods")
    public String addGoods(Model model, @RequestParam("code") Integer code, @RequestParam("name") String name,
    		@RequestParam("quant") Double quant, @RequestParam("price") Double price, @RequestParam("measure") String measure,
    		@RequestParam("comments") String comments,    		
    		@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
    	Long goodsId = goodsService.addGoods(code, name, quant,	price, measure, comments);
		if (goodsId > 0) {
			model.addAttribute("addedGood", name);				
		} else if (goodsId == -1) {
			model.addAttribute("addedGood", null);
			model.addAttribute("existsCode", code);
		} else if (goodsId == -2) {
			model.addAttribute("addedGood", null);
			model.addAttribute("existsName", name);
		}addPagination(model, page, size);
    	return "/goods";
    }
    
    @GetMapping("/goods/edit/{code}")
    public String editGoods(Model model, @PathVariable Integer code, @RequestParam("page") Optional<Integer> page, 
    	      @RequestParam("size") Optional<Integer> size) {
    	model.addAttribute("editCode", code);
        addPagination(model, page, size);
        return "/goods";
    }
    
	@PostMapping("/goods/edit/{code}")
    public ModelAndView updateGoods(Model model, @PathVariable Integer code,
    		@RequestParam("changequant") Double changequant, @RequestParam("changeprice") Double changeprice,  
    		@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		goodsService.changeGoods(code, changequant, changeprice);
		
        addPagination(model, page, size);
        return new ModelAndView("redirect:/goods");
    }
    
    /**
	 * @param model
	 * @param page
	 * @param size
	 */
	private void addPagination(Model model, Optional<Integer> page, Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
		Page<Goods> goods = goodsService.view(currentPage, pageSize);
		model.addAttribute("goods", goods);
		model.addAttribute("currentPage", currentPage);
		int totalPages = goods.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					.boxed()
	                .collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
	}
}
