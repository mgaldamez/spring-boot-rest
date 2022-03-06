/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.controller;

import com.py.springbootrestblog.dto.CustomResponse;
import com.py.springbootrestblog.dto.UserDTO;
import com.py.springbootrestblog.model.User;
import com.py.springbootrestblog.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    public ResponseEntity<CustomResponse<List<UserDTO>>> findAll(
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {

        CustomResponse<List<UserDTO>> response = new CustomResponse<>();

        try {
            List<User> list = userService.findAll(sortBy, sortDir);
            List<UserDTO> listDTO = list.stream().map(dto -> new UserDTO(dto))
                    .collect(Collectors.toList());

            response.setMessage("Users listing obtain sucessfully");
            response.setError(false);
            response.setData(listDTO);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Users listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/paginated")
    public ResponseEntity<CustomResponse<Page<UserDTO>>> paginated(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        CustomResponse<Page<UserDTO>> response = new CustomResponse<>();

        try {
            Page<User> list = userService.paginated(page, size, sortBy, sortDir);
            Page<UserDTO> listDto = list.map(UserDTO::new);

            response.setMessage("Users listing obtain sucessfully");
            response.setError(false);
            response.setData(listDto);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("Users listing obtain unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<UserDTO>> findById(@PathVariable("id") Long id) {
        CustomResponse<UserDTO> response = new CustomResponse<>();

        try {
            Optional<User> entity = userService.findById(id);
            if (entity.isPresent()) {
                response.setMessage("User obtain sucessfully");
                response.setError(false);
                response.setData(new UserDTO(entity.get()));
            } else {
                response.setMessage("User not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("User obtain unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<UserDTO>> save(@RequestBody UserDTO body) {
        CustomResponse<UserDTO> response = new CustomResponse<>();

        try {

            if (userService.existsByUsername(body.getUsername())) {
                response.setMessage("Username is already taken");
                response.setError(true);
                response.setData(null);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }

            if (userService.existsByEmail(body.getEmail())) {
                response.setMessage("Email is already in use");
                response.setError(true);
                response.setData(null);
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            body.setPassword(encoder.encode(body.getPassword()));

            User persist = userService.save(body.build());

            response.setMessage("User create sucessfully");
            response.setError(false);
            response.setData(new UserDTO(persist));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("User create unsucessfully: " + e.getMessage());
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<UserDTO>> update(@RequestBody UserDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<UserDTO> response = new CustomResponse<>();

        try {
            Optional<User> entity = userService.findById(id);
            if (entity.isPresent()) {
                body.setId(id);
                User persist = userService.save(body.build());

                response.setMessage("User update sucessfully");
                response.setError(false);
                response.setData(new UserDTO(persist));
            } else {
                response.setMessage("User not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("User update unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<UserDTO>> delete(@RequestBody UserDTO body,
            @PathVariable("id") Long id) {
        CustomResponse<UserDTO> response = new CustomResponse<>();

        try {
            Optional<User> entity = userService.findById(id);
            if (entity.isPresent()) {
                userService.delete(id);

                response.setMessage("User delete sucessfully");
                response.setError(false);
                response.setData(null);
            } else {
                response.setMessage("User not found");
                response.setError(true);
                response.setData(null);
            }

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {

            response.setMessage("User delete unsucessfully");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

}
