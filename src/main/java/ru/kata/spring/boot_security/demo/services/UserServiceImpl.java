package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    // прописать валидацию user

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        if (userRepository.findByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException(String.format("Пользователь c именем '%s'  не найден", username));
        }
        return userRepository.findByUsername(username).get();

    }

    @Override
    public User findUserById(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UsernameNotFoundException(String.format("Пользователь с ID = '%s' не найден", id));
        }
        return userRepository.findById(id).get();
    }

    @Override
    public void updateUser(User user, Long id) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public boolean deleteUserById(Long id) {
        return false;
    }
}
