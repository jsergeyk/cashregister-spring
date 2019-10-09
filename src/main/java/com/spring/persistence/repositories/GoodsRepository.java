package com.spring.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.persistence.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

	/**
	 * @param code код товара
	 * @return товар
	 */
	Optional<Goods> findByCode(int code);

	/**
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
	@Modifying
	@Query("Update Goods g SET g.quant=g.quant- :quant WHERE g.id=:id")	
	Integer reduceQuant(Long id, double quant);
}
