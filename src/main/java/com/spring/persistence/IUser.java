package com.spring.persistence;


import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;

public interface IUser {

    List<User> findAllUser();

	/**
	 * @param login
	 * @param password
	 * @return User
	 */
	User findByLoginAndPassword(String login, String password);
	
    Optional<User> findById(Long id);

    List<User> findByUserType(UserType userType);
    
    User save(User user);
}
