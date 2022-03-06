/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.dto;

import com.py.springbootrestblog.model.UserRole;

/**
 *
 * @author Favio Amarilla
 */
public class UserRoleDTO {

    private Long id;
    private UserDTO user;
    private RoleDTO role;

    public UserRoleDTO() {
    }

    public UserRoleDTO(Long id, UserDTO user, RoleDTO role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public UserRoleDTO(UserRole entity) {
        this.id = entity.getId();
        this.user = new UserDTO(entity.getUser());
        this.role = new RoleDTO(entity.getRole());
    }

    public UserRole build() {
        UserRole entity = new UserRole();
        entity.setId(this.id);
        entity.setUser(this.user.build());
        entity.setRole(this.role.build());

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

}
