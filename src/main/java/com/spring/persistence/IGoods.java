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

}
