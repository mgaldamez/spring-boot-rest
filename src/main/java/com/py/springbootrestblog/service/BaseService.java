/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 *
 * @author Favio Amarilla
 */
public interface BaseService<T> {

    public List<T> findAll(String sortBy, String sortDir);

    public Page<T> paginated(Integer page, Integer size, String sortBy, String sortDir);

    public Optional<T> findById(Long id);

    public T save(T object);

    public T update(T object, Long id);

    public void delete(Long id);

}
