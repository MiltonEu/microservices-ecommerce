package com.eustache.user.service;

import com.eustache.user.model.abstraction.User;
import com.eustache.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }

    @Override
    public Boolean deleteUserById(Integer userId) {
        Optional<User> articleToDelete = userRepository.findById(userId);
        if (articleToDelete.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
