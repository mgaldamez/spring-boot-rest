/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service.impl;

import com.py.springbootrestblog.model.Role;
import com.py.springbootrestblog.repository.RoleRepository;
import com.py.springbootrestblog.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll(String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        return roleRepository.findAll(sort);
    }

    @Override
    public Page<Role> paginated(Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return roleRepository.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(Long id) {

        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role object) {

        return roleRepository.save(object);
    }

    @Override
    public Role update(Role object, Long id) {

        return roleRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

}
