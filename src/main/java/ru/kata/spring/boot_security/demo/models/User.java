package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "yearOfBirth")
    private Integer yearOfBirth;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public User() {}

    public User(Long id, String username, Integer yearOfBirth, String email, String password) {
        this.id = id;
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer year_of_birth) {
        this.yearOfBirth = year_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", year_of_birth=" + yearOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
