/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.controller;

import com.py.springbootrestblog.dto.CustomResponse;
import com.py.springbootrestblog.dto.CommentDTO;
import com.py.springbootrestblog.model.Comment;
import com.py.springbootrestblog.model.Publication;
import com.py.springbootrestblog.service.CommentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Favio Amarilla
 */
@RestController
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<CommentDTO>>> findAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        CustomResponse<List<CommentDTO>> response = new CustomResponse<>();

        try {
            List<Comment> list = commentService.findAll(sortBy, sortDir);
            List<CommentDTO> listDTO = list.stream().map(dto -> new CommentDTO(dto))
                    .collect(Collectors.toList());

            response.setMessage("Comments listing obtain sucessfully");
            response.setError(false);
            response.setData(listDTO);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comments listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/paginated")
    public ResponseEntity<CustomResponse<Page<CommentDTO>>> paginated(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        CustomResponse<Page<CommentDTO>> response = new CustomResponse<>();

        try {
            Page<Comment> list = commentService.paginated(page, size, sortBy, sortDir);
            Page<CommentDTO> listDTO = list.map(CommentDTO::new);

            response.setMessage("Comments listing obtain sucessfully");
            response.setError(false);
            response.setData(listDTO);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comments listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/publication/{id}")
    public ResponseEntity<CustomResponse<List<CommentDTO>>> findByPublication(@PathVariable("id") Long id) {

        CustomResponse<List<CommentDTO>> response = new CustomResponse<>();

        try {
            List<Comment> list = commentService.findByPublication(new Publication(id));
            List<CommentDTO> listDTO = list.stream().map(dto -> new CommentDTO(dto))
                    .collect(Collectors.toList());

            response.setMessage("Comments listing obtain sucessfully");
            response.setError(false);
            response.setData(listDTO);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comments listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<CommentDTO>> findById(@PathVariable("id") Long id) {
        CustomResponse<CommentDTO> response = new CustomResponse<>();

        try {
            Optional<Comment> entity = commentService.findById(id);
            if (entity.isPresent()) {
                response.setMessage("Comment obtain sucessfully");
                response.setError(false);
                response.setData(new CommentDTO(entity.get()));
            } else {
                response.setMessage("Comment not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comment obtain unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<CommentDTO>> save(@RequestBody CommentDTO body) {
        CustomResponse<CommentDTO> response = new CustomResponse<>();

        try {
            Comment persist = commentService.save(body.build());

            response.setMessage("Comment create sucessfully");
            response.setError(false);
            response.setData(new CommentDTO(persist));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comment create unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<CommentDTO>> update(@RequestBody CommentDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<CommentDTO> response = new CustomResponse<>();

        try {
            Optional<Comment> entity = commentService.findById(id);
            if (entity.isPresent()) {
                body.setId(id);
                Comment persist = commentService.save(body.build());

                response.setMessage("Comment update sucessfully");
                response.setError(false);
                response.setData(new CommentDTO(persist));
            } else {
                response.setMessage("Comment not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comment update unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<CommentDTO>> delete(@RequestBody CommentDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<CommentDTO> response = new CustomResponse<>();

        try {
            Optional<Comment> entity = commentService.findById(id);
            if (entity.isPresent()) {
                commentService.delete(id);

                response.setMessage("Comment delete sucessfully");
                response.setError(false);
                response.setData(null);
            } else {
                response.setMessage("Comment not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Comment delete unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}
