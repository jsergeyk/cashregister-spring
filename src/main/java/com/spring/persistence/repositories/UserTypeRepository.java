package com.spring.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
