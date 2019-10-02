package com.spring.persistence.springdata;

import com.spring.persistence.IUser;
import com.spring.persistence.entity.User;
import com.spring.persistence.entity.UserType;
import com.spring.persistence.repositories.UserRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UserImpl implements IUser {

	private final UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

	@Override
	public List<User> findByUserType(UserType userType) {
		return userRepository.findUserByUserType(userType);
	}

	@Override
	public User findByLoginAndPassword(String login, String password) {
		return userRepository.findByLoginAndPassword(login, password);
	}
}
