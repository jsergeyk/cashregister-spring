package com.spring.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.Checkspec;

public interface CheckspecRepository extends JpaRepository<Checkspec, Long> {
}
