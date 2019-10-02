package com.spring.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.Chec;

public interface ChecRepository extends JpaRepository<Chec, Long> {
}
