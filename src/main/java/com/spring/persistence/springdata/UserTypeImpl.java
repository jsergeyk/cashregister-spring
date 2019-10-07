package com.spring.persistence.springdata;

import com.spring.persistence.IUserType;
import com.spring.persistence.entity.UserType;
import com.spring.persistence.repositories.UserTypeRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserTypeImpl implements IUserType {

	private final UserTypeRepository userTypeRepository;
	
	public UserTypeImpl(UserTypeRepository userTypeRepository) {
		this.userTypeRepository = userTypeRepository;
	}

    @Override
    public List<UserType> findAllUserType() {
        return userTypeRepository.findAll();
    }

    @Override
    public Optional<UserType> findById(Long id) {
        return userTypeRepository.findById(id);
    }

	@Override
	public Optional<UserType> findByType(String type) {
		return userTypeRepository.findByType(type);
	}
	
    @Override
    public UserType save(UserType userType) {
        return userTypeRepository.save(userType);
    }
}
