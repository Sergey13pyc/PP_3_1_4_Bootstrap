package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String username);

    User findByEmail(String email);

    User findUserById(Long id);

    void updateUser(User user, Long id);

    void saveUser(User user);

    void deleteUserById(Long id);

}
