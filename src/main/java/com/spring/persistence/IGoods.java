package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spring.persistence.entity.Goods;

public interface IGoods {

    List<Goods> findAllGoods();
	
    Goods save(Goods goods);
    
	List<Goods> findAll();
	
	Page<Goods> findAll(Pageable p);
	
    Optional<Goods> findById(Long id);

	/**
	 * Найти товар по коду товара
	 * @param name код товара
	 * @return товар
	 */
    Optional<Goods> findByCode(int code);
    
	/**
	 * Найти товар по наименованию
	 * @param name наименование товара
	 * @return товар
	 */
	Optional<Goods> findByName(String name);

	
	/**
	 * Уменьшить количество товара 
	 * @param id id товара
	 * @param quant количество товара, на которое уменьшается
	 * @return количество обработанных записей
	 */
	Integer reduceQuant(Long id, double quant);
}
