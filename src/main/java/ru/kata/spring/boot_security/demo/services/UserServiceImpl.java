package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    // прописать валидацию user

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        if (usersRepository.findByUsername(username).isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        }
        return usersRepository.findByUsername(username).get();

    }

    @Override
    public User findUserById(Long id) {
        if (usersRepository.findById(id).isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким ID не найден");
        }
        return usersRepository.findById(id).get();
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
