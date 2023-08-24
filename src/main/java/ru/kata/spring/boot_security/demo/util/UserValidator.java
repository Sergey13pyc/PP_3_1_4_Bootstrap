package ru.kata.spring.boot_security.demo.util;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.MyUserDetailsService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UserService userService;

    public UserValidator( UserService userService) {
        this.userService = userService;
        ;    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String username = user.getUsername();
        Optional<User> existingUser = userService.findByUsername(username);
        if (existingUser.isPresent()) {
            errors.rejectValue("username", "username.alreadyExists", "Пользователь с таким именем уже существует!");
        }


    }
}
