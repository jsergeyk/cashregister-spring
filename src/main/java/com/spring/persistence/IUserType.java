package com.spring.persistence;

import java.util.List;
import java.util.Optional;

import com.spring.persistence.entity.UserType;

public interface IUserType {

    List<UserType> findAllUserType();

    Optional<UserType> findById(Long id);
    
    Optional<UserType> findByType(String type);
    
    UserType save(UserType user);
}
