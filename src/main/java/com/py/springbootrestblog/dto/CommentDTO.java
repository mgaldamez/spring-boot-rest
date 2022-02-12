/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.dto;

import com.py.springbootrestblog.model.Comment;
import com.py.springbootrestblog.model.Publication;

/**
 *
 * @author Favio Amarilla
 */
public class CommentDTO {

    private Long id;
    private String content;
    private PublicationDTO publication;

    public CommentDTO() {
    }

    public CommentDTO(Comment entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.publication = new PublicationDTO(entity.getPublication());
    }

    public Comment build() {
        Comment entity = new Comment();
        entity.setId(this.id);
        entity.setContent(this.content);
        entity.setPublication(this.publication.build());

        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PublicationDTO getPublication() {
        return publication;
    }

    public void setPublication(PublicationDTO publication) {
        this.publication = publication;
    }

}
