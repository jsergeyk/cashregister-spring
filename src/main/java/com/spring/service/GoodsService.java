package com.spring.service;

import com.spring.persistence.IGoods;
import com.spring.persistence.entity.Goods;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GoodsService {

	private final IGoods goodsDAO;    

    public Optional<Goods> findByCode(int code) {
        return goodsDAO.findByCode(code);
    }

    public Optional<Goods> findById(Long id) {
        return goodsDAO.findById(id);
    }

    /**
     * 
     * @param page отображаемая страница
     * @param recordsPerPage кол-во записей на страницу
     * @return Page<Goods> страница
     */
    public Page<Goods> view(int page, int recordsPerPage) {
    	PageRequest sortedByCode = PageRequest.of(page, recordsPerPage, Sort.by("code"));
    	return goodsDAO.findAll(sortedByCode);
	}
    
    public void save(Goods goods) {
    	goodsDAO.save(goods);
    }

}
