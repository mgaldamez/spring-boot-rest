/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service.impl;

import com.py.springbootrestblog.dto.CustomResponse;
import com.py.springbootrestblog.dto.PublicationDTO;
import com.py.springbootrestblog.model.Publication;
import com.py.springbootrestblog.repository.PublicationRepository;
import com.py.springbootrestblog.service.PublicationService;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Favio Amarilla
 */
@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public List<Publication> findAll() {

        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> findById(Long id) {

        return publicationRepository.findById(id);
    }

    @Override
    public Publication save(Publication object) {

        return publicationRepository.save(object);
    }

    @Override
    public Publication update(Publication object, Long id) {

        return publicationRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        publicationRepository.deleteById(id);
    }

}
