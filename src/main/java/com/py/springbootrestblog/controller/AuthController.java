/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.py.springbootrestblog.controller;

import com.py.springbootrestblog.dto.CustomResponse;
import com.py.springbootrestblog.security.dto.ERole;
import com.py.springbootrestblog.dto.UserDTO;
import com.py.springbootrestblog.model.Role;
import com.py.springbootrestblog.model.User;
import com.py.springbootrestblog.repository.RoleRepository;
import com.py.springbootrestblog.repository.UserRepository;
import com.py.springbootrestblog.security.jwt.JwtUtils;
import com.py.springbootrestblog.service.impl.UserDetailsImpl;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Favio Amarilla
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<CustomResponse<UserDTO>> authenticateUser(@Valid @RequestBody UserDTO userDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> rolesList = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        Set<String> roles = new HashSet<>(rolesList);

        UserDTO userRsponse = new UserDTO(userDetails.getId(), userDetails.getName(),
                userDetails.getUsername(), userDetails.getEmail(), null, roles, jwt);

        CustomResponse<UserDTO> response = new CustomResponse<>();
        response.setMessage("Access sucessfully");
        response.setError(false);
        response.setData(userRsponse);
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/signup")
    public ResponseEntity<CustomResponse<UserDTO>> registerUser(@Valid @RequestBody UserDTO userDTO) {

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            CustomResponse<User> response = new CustomResponse<>();
            response.setMessage("Username is already taken");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            CustomResponse<User> response = new CustomResponse<>();
            response.setMessage("Email is already in use");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        User user = new User(userDTO.getName(), userDTO.getUsername(), userDTO.getEmail(), encoder.encode(userDTO.getPassword()));
        Set<String> strRoles = userDTO.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            CustomResponse<User> response = new CustomResponse<>();
            response.setMessage("Role is not found");
            response.setError(true);
            response.setData(null);
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } else {
            for (String strRole : strRoles) {
                switch (strRole) {
                    case "ROLE_ADMIN":
                        Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        if (!adminRole.isPresent()) {
                            CustomResponse<User> response = new CustomResponse<>();
                            response.setMessage("Role is not found");
                            response.setError(true);
                            response.setData(null);
                            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                        }

                        roles.add(adminRole.get());
                        break;

                    default:
                        Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_USER);
                        if (!userRole.isPresent()) {
                            CustomResponse<User> response = new CustomResponse<>();
                            response.setMessage("Role is not found");
                            response.setError(true);
                            response.setData(null);
                            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
                        }

                        roles.add(userRole.get());
                        break;
                }
            }
        }
        user.setRoles(roles);

        UserDTO userResponse = new UserDTO(userRepository.save(user));
        userResponse.setRole(strRoles);
        userResponse.setPassword(null);

        CustomResponse<UserDTO> response = new CustomResponse<>();
        response.setMessage("User registered successfully");
        response.setError(false);
        response.setData(userResponse);

        return authenticateUser(userDTO);
    }
}
