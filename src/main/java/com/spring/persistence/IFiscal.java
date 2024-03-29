package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.Fiscal;

public interface IFiscal {

    List<Fiscal> findAllFiscal();

    Optional<Fiscal> findById(Long id);

    Fiscal save(Fiscal fiscal);

	/**
	 * Сформировать Х-отчет
	 * @return
	 */
    List<Object[]> createXReport();
    
	/**
	 * Сформировать Z-отчет
	 * @return
	 */
    List<Object[]> createZReport();
}
