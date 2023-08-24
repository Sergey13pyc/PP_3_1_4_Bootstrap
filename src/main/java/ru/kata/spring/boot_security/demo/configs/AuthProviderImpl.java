package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.services.MyUserDetailsService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public AuthProviderImpl(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails myUserDetails = myUserDetailsService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(myUserDetails.getPassword()))
            throw new BadCredentialsException("Введён неверный пороль!");

// Объект Аутентификейшн содержит (данные о пользователе(principal), пороль и список прав/ролей
        return new UsernamePasswordAuthenticationToken(myUserDetails, password, Collections.emptyList());
    }


    // В этом методе мы можем сказать в каком сценарии
    // какой AuthProvider должен быть использован, если их несколько.
    // пока будем возвращать true
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
