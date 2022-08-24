package com.eustache.user.service;

import com.eustache.user.model.abstraction.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    Optional<User> findUserById(Integer userId) ;
    User saveUser(User userToSave);
    Boolean deleteUserById(Integer userId);
}
