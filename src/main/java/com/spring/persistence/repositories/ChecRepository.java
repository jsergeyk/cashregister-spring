package com.spring.persistence.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spring.persistence.entity.Chec;

public interface ChecRepository extends JpaRepository<Chec, Long> {

	/**
	 * @param date
	 */
	@Modifying
	@Query("Update Chec c SET c.registration=1 WHERE DATE(crtime) = DATE(:date)")
	void register(Date date);
}
