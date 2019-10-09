package com.spring.persistence;


import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;

public interface IUser {

    List<User> findAllUser();

	/**
	 * Найти пользователя по логину и паролю
	 * @param login логин
	 * @param password пароль
	 * @return User пользователь
	 */
	User findByLoginAndPassword(String login, String password);
	
	/**
	 * Найти пользователя по логину
	 * @param login логин
	 * @return User пользователь
	 */
	User findByLogin(String login);
	
    Optional<User> findById(Long id);

    List<User> findByUserType(UserType userType);
    
    User save(User user);
}
