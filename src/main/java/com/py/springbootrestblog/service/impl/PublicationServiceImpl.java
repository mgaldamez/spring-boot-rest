/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service.impl;

import com.py.springbootrestblog.model.Publication;
import com.py.springbootrestblog.repository.PublicationRepository;
import com.py.springbootrestblog.service.PublicationService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Publication> findAll(String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        
        return publicationRepository.findAll(sort);
    }

    @Override
    public Page<Publication> paginated(Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return publicationRepository.findAll(pageable);
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
