package com.spring.service;

import com.spring.persistence.IUser;
import com.spring.persistence.IUserType;
import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

	private final IUser userDAO;
    private final IUserType iUserType;    

    public User findByLoginAndPassword(String login, String password) {
        return userDAO.findByLoginAndPassword(login, password);
    }
    
    public List<User> findByUserType(UserType userType) {
        return userDAO.findByUserType(userType);
    }

    public User findById(Long id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("Wrong id"));
    }

    public void save(User user, UserType userType) {
    	UserType savedUserType = iUserType.save(userType);
    	user.setUserType(savedUserType);
        //User savedUser = userDAO.save(user);
    }

}
