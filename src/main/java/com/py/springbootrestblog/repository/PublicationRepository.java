/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.py.springbootrestblog.repository;

import com.py.springbootrestblog.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Favio Amarilla
 */
@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}
