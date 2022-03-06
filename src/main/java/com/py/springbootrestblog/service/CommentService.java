/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.py.springbootrestblog.service;

import com.py.springbootrestblog.model.Comment;
import com.py.springbootrestblog.model.Publication;
import java.util.List;

/**
 *
 * @author Favio Amarilla
 */
public interface CommentService extends BaseService<Comment> {

    public List<Comment> findByPublication(Publication publication);

}
