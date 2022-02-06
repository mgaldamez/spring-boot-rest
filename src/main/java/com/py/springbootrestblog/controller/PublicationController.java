/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.controller;

import com.py.springbootrestblog.dto.CustomResponse;
import com.py.springbootrestblog.dto.PublicationDTO;
import com.py.springbootrestblog.model.Publication;
import com.py.springbootrestblog.service.PublicationService;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
@RequestMapping("api/publication")
public class PublicationController {

    ModelMapper mapper = new ModelMapper();

    Type typeDTO = new TypeToken<List<PublicationDTO>>() {
    }.getType();

    @Autowired
    PublicationService publicationService;

    @GetMapping
    public ResponseEntity<CustomResponse<Page<Publication>>> findAll(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size
    ) {
        CustomResponse<Page<Publication>> response = new CustomResponse<>();

        try {
            Page<Publication> list = publicationService.findAll(page, size);

            response.setMessage("Publications listing obtain sucessfully");
            response.setError(false);
            response.setData(list);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Publications listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<PublicationDTO>> findById(@PathVariable("id") Long id) {
        CustomResponse<PublicationDTO> response = new CustomResponse<>();

        try {
            Optional<Publication> entity = publicationService.findById(id);
            if (entity.isPresent()) {
                response.setMessage("Publication obtain sucessfully");
                response.setError(false);
                response.setData(mapper.map(entity.get(), PublicationDTO.class));
            } else {
                response.setMessage("Publication not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Publication obtain unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<PublicationDTO>> save(@RequestBody PublicationDTO body) {
        CustomResponse<PublicationDTO> response = new CustomResponse<>();

        try {
            Publication persist = publicationService.save(mapper.map(body, Publication.class));

            response.setMessage("Publication create sucessfully");
            response.setError(false);
            response.setData(mapper.map(persist, PublicationDTO.class));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Publication create unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<PublicationDTO>> update(@RequestBody PublicationDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<PublicationDTO> response = new CustomResponse<>();

        try {
            Optional<Publication> entity = publicationService.findById(id);
            if (entity.isPresent()) {
                body.setId(id);
                Publication persist = publicationService.save(mapper.map(body, Publication.class));

                response.setMessage("Publication update sucessfully");
                response.setError(false);
                response.setData(mapper.map(persist, PublicationDTO.class));
            } else {
                response.setMessage("Publication not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Publication update unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<PublicationDTO>> delete(@RequestBody PublicationDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<PublicationDTO> response = new CustomResponse<>();

        try {
            Optional<Publication> entity = publicationService.findById(id);
            if (entity.isPresent()) {
                publicationService.delete(id);

                response.setMessage("Publication delete sucessfully");
                response.setError(false);
                response.setData(null);
            } else {
                response.setMessage("Publication not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Publication delete unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}
