package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;

@Controller
public class UsersController {

    @GetMapping("/user")
    public String sayHello () {
        return "user";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
        System.out.println(myUserDetails.getUser());
        return "user";

    }
}
