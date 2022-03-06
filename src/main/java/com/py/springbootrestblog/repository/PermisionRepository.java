/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.repository;

import com.py.springbootrestblog.model.Permision;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Favio Amarilla
 */
@Repository
public interface PermisionRepository extends JpaRepository<Permision, Long>, PagingAndSortingRepository<Permision, Long> {

    Optional<Permision> findByName(String name);
}
