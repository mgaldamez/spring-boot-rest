/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.dto;

import com.py.springbootrestblog.model.Permision;

/**
 *
 * @author Favio Amarilla
 */
public class PermisionDTO {

    private Long id;
    private String name;

    public PermisionDTO() {
    }

    public PermisionDTO(Permision entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Permision build() {
        Permision entity = new Permision();
        entity.setId(this.id);
        entity.setName(this.name);

        return entity;
    }

    public PermisionDTO(Long id, String name, String username, String email, String password) {
        this.id = id;
        this.name = name;
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

}
