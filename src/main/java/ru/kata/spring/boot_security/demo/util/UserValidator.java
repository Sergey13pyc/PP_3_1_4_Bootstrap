package ru.kata.spring.boot_security.demo.util;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


@Component
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        String email = user.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            errors.rejectValue("email", "email.alreadyExists",
                    "Пользователь с таким адресом почты уже существует!");
        }


    }
}
