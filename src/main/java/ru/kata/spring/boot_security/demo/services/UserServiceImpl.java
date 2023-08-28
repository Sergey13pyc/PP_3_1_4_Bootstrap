package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // прописать валидацию user

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    @Override
    public void updateUser(User updatedUser, Long id) {
        updatedUser.setId(id);
        updatedUser.setRoles(updatedUser.getRoles());
        updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        userRepository.save(updatedUser);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        // Шифрование пароля с использованием PasswordEncoder перед сохранением пользователя
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);

    }
}
