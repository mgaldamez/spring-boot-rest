/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.dto;

import com.py.springbootrestblog.model.User;
import java.util.List;

/**
 *
 * @author Favio Amarilla
 */
public class LoginDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private List<String> permisions;
    private String token;

    public LoginDTO() {
    }

    public LoginDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    public User build() {
        User entity = new User();
        entity.setId(this.id);
        entity.setName(this.name);
        entity.setUsername(this.username);
        entity.setEmail(this.email);

        return entity;
    }

    public LoginDTO(Long id, String name, String username, String email, String password, List<String> permisions, String token) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.permisions = permisions;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<String> getPermisions() {
        return permisions;
    }

    public void setPermisions(List<String> permisions) {
        this.permisions = permisions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
