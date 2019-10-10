package com.spring.persistence.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.Fiscal;

public interface FiscalRepository extends JpaRepository<Fiscal, Long> {
	
	/**
	 * Создать Х-отчет
	 * @return
	 */
	List<Object[]> createXReport();

	/**
	 * Создать Z-отчет
	 * @return
	 */
	List<Object[]> createZReport();
}
