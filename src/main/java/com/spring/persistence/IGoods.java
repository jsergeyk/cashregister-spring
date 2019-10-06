package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.persistence.entity.Goods;

public interface IGoods {

    List<Goods> findAllGoods();
    Optional<Goods> findByCode(int code);
    Optional<Goods> findById(Long id);
	/**
	 * @param name
	 * @return
	 */
	Optional<Goods> findByName(String name);
    Goods save(Goods goods);
    
	/**
	 * @return
	 */
	List<Goods> findAll();
	
	/**
	 * @param p
	 * @return
	 */
	Page<Goods> findAll(Pageable p);
	/**
	 * @param id
	 * @param quant
	 */
	Integer reduceQuant(Long id, double quant);
}
