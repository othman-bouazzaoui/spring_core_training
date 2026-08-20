package com.oth.service;

import com.oth.dao.jdbc.UserRepositoryJdbc;
import com.oth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = loadUsers();

    @Autowired
    private UserRepositoryJdbc userRepositoryJdbc;

    @Override
    public List<User> getAllUsers() {
        return userRepositoryJdbc.findAll();
    }

    @Override
    public User createUser(User user) {
        //user.setId(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
        //users.add(user);
        return userRepositoryJdbc.createUser(user);
    }

    @Override
    public User modifyUser(User user) {
        //users.removeIf(u -> Objects.nonNull(user.getId()) && user.getId().equals(u.getId()));
        //users.add(user);
        return userRepositoryJdbc.modifyUser(user);
    }

    @Override
    public int deleteUser(Long id) {
        //users.removeIf(user -> Objects.nonNull(id) && id.equals(user.getId()));
        return userRepositoryJdbc.deleteUserById(id);
    }

    @Override
    public User findUserById(Long id) {
        return userRepositoryJdbc.findUserById(id);
    }

    private List<User> loadUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        User u1 = new User(1L, "Othman", "BOUAZZAOUI", 29L);
        User u2 = new User(2L, "Mohamed", "Hamed", 35L);
        User u4 = new User(4L, "Khalid", "Somayla", 32L);
        User u3 = new User(3L, "Saad", "Chaaban", 25L);
        usersList.add(u1);
        usersList.add(u2);
        usersList.add(u3);
        usersList.add(u4);
        return usersList;
    }

}
