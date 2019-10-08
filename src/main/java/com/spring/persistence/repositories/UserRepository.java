package com.spring.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findUserByUserType(UserType userType);

	/**
	 * @param login логин пользователя
	 * @param password пароль пользователя
	 * @return user пользователь
	 */
	User findByLoginAndPassword(String login, String password);

	/**
	 * @param login логин пользователя
	 * @return user пользователь
	 */
	User findByLogin(String login);
}
