/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.service.impl;

import com.py.springbootrestblog.model.Comment;
import com.py.springbootrestblog.model.Publication;
import com.py.springbootrestblog.repository.CommentRepository;
import com.py.springbootrestblog.service.CommentService;
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
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findAll(String sortBy, String sortDir) {
        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        return commentRepository.findAll(sort);
    }

    @Override
    public Page<Comment> paginated(Integer page, Integer size, String sortBy, String sortDir) {
        Sort sort = Sort.by("asc".equals(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return commentRepository.findAll(pageable);
    }

    @Override
    public List<Comment> findByPublication(Publication publication) {
        return commentRepository.findByPublicationOrderByIdDesc(publication);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment object) {
        return commentRepository.save(object);
    }

    @Override
    public Comment update(Comment object, Long id) {
        return commentRepository.save(object);
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

}
