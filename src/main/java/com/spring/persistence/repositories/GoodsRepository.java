package com.spring.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.Goods;

public interface GoodsRepository extends JpaRepository<Goods, Long> {

	/**
	 * @param code
	 * @return
	 */
	Optional<Goods> findByCode(int code);
}
