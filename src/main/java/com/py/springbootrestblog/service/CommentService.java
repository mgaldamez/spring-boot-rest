/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.py.springbootrestblog.service;

import com.py.springbootrestblog.model.Comment;
import com.py.springbootrestblog.model.Publication;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 *
 * @author Favio Amarilla
 */
public interface CommentService {

    public List<Comment> findAll(String sortBy, String sortDir);

    public Page<Comment> paginated(Integer page, Integer size, String sortBy, String sortDir);

    public List<Comment> findByPublication(Publication publication);

    public Optional<Comment> findById(Long id);

    public Comment save(Comment object);

    public Comment update(Comment object, Long id);

    public void delete(Long id);
}
