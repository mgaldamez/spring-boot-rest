/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service;

import com.py.springbootrestblog.model.User;

/**
 *
 * @author Favio Amarilla
 */
public interface UserService extends BaseService<User> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String username);
}
