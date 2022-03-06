/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service.impl;

import com.py.springbootrestblog.model.UserRole;
import com.py.springbootrestblog.repository.UserRoleRepository;
import com.py.springbootrestblog.service.UserRoleService;
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
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userUserRoleRepository;

    @Override
    public List<UserRole> findAll(String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);

        return userUserRoleRepository.findAll(sort);
    }

    @Override
    public Page<UserRole> paginated(Integer page, Integer size, String sortBy, String sortDir) {

        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return userUserRoleRepository.findAll(pageable);
    }

    @Override
    public Optional<UserRole> findById(Long id) {

        return userUserRoleRepository.findById(id);
    }

    @Override
    public UserRole save(UserRole object) {

        return userUserRoleRepository.save(object);
    }

    @Override
    public UserRole update(UserRole object, Long id) {

        return userUserRoleRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        userUserRoleRepository.deleteById(id);
    }

}
