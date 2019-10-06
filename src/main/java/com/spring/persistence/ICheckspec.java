package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.Checkspec;

public interface ICheckspec {

    List<Checkspec> findAllCheckspec();

    Optional<Checkspec> findById(Long id);

    Checkspec save(Checkspec check);

	/**
	 * @param checkspecs
	 */
	void saveAll(Iterable<Checkspec> entities);
}
