package com.oth.service;

import com.oth.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User modifyUser(User user);
    int deleteUser(Long id);
    User findUserById(Long id);

}
