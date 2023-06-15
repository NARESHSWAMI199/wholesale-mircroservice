package com.auth.services;


import com.auth.entity.User;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.UUID;


@Service
public class UserService extends BaseService {

    public User findUserBySlug(String slug) {
        return userRepository.findBySlug(slug);
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NoResultException("Entry not found"));
    }


    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    public User saveUser(User user) {
        user.setSlug(UUID.randomUUID().toString());
        return userRepository.save(user);
    }


    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }


}
