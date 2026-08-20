package com.oth.dao.jdbc;

import com.oth.model.User;

import java.util.List;

public interface UserRepositoryJdbc {
    User  findUserById(Long id);
    List<User> findAll();
    User  createUser(User user);
    User  modifyUser(User user);

    int deleteUserById(Long id);
}
