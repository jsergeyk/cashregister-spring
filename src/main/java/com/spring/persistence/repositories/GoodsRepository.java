package com.spring.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.persistence.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

	/**
	 * @param code
	 * @return
	 */
	Optional<Goods> findByCode(int code);

	/**
	 * @param name
	 * @return
	 */
	Optional<Goods> findByName(String name);

	/**
	 * @param id
	 * @param quant
	 * @return
	 */
	@Modifying
	@Query("Update Goods g SET g.quant=g.quant- :quant WHERE g.id=:id")
	Integer reduceQuant(Long id, double quant);
}
