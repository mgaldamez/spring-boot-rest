/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.py.springbootrestblog.service;

import com.py.springbootrestblog.model.Publication;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 *
 * @author Favio Amarilla
 */
public interface PublicationService {

    public Page<Publication> findAll(Integer page, Integer size);

    public Optional<Publication> findById(Long id);

    public Publication save(Publication object);

    public Publication update(Publication object, Long id);

    public void delete(Long id);
}
