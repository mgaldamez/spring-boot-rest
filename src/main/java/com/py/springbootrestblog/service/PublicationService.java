/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.py.springbootrestblog.service;

import com.py.springbootrestblog.model.Publication;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Favio Amarilla
 */
public interface PublicationService {

    public List<Publication> findAll();

    public Optional<Publication> findById(Long id);

    public Publication save(Publication object);

    public Publication update(Publication object, Long id);

    public void delete(Long id);
}
