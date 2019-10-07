package com.spring.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	/**
	 * Найти тип пользователя
	 * @param type тип пользователя
	 * @return UserType
	 */
	Optional<UserType> findByType(String type);
}
