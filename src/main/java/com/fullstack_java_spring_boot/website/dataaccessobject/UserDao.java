package com.fullstack_java_spring_boot.website.dataaccessobject;

import com.fullstack_java_spring_boot.website.models.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    void delete(Long id);

    void registerUser(User user);

    User ifExitsReturnUserObject(User user);
}
