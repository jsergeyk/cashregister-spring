package com.spring.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.Fiscal;

public interface FiscalRepository extends JpaRepository<Fiscal, Long> {
}
