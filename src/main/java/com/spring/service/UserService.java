package com.spring.service;

import com.spring.persistence.IUser;
import com.spring.persistence.IUserType;
import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

	private final IUser userDAO;
    private final IUserType userTypeDAO;    

    @Transactional(readOnly = true)
    public User findByLoginAndPassword(String login, String password) {
        return userDAO.findByLoginAndPassword(login, password);
    }
    
    @Transactional(readOnly = true)
    public List<User> findByUserType(UserType userType) {
        return userDAO.findByUserType(userType);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("Wrong id"));
    }

	/**
	 * Регистрация нового пользователя и типа пользователя
	 * @param user пользователь
	 * @param userType тип пользователь
	 * @return user пользователь
	 */
    public User save(User user, UserType userType) {
    	UserType savedUserType = userTypeDAO.save(userType);
    	user.setUserType(savedUserType);
    	return userDAO.save(user);
    }

	/**
	 * Регистрация нового пользователя (по-умолчанию с типом кассир)
	 * @param user пользователь
	 * @return user пользователь
	 */
	public User registration(User user) {
		if (userDAO.findByLogin(user.getLogin()) == null) {
			Optional<UserType> type = userTypeDAO.findByType("cashier");
			if (type.isPresent()) {
				user.setUserType(type.get());
				return userDAO.save(user);
			}
		}
		return null;
	}
}
