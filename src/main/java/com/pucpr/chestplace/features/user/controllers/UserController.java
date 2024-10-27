package com.pucpr.chestplace.features.user.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pucpr.chestplace.features.user.dtos.UserGetResponseDTO;
import com.pucpr.chestplace.features.user.dtos.UserRegisterRequestDTO;
import com.pucpr.chestplace.features.user.dtos.UserUpdateRequestDTO;
import com.pucpr.chestplace.features.user.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private final UserService authService;

    public UserController(UserService service) {
        this.authService = service;
    }


    /*
     * 
     * ENDPOINTS
     * 
     */

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody @Valid UserRegisterRequestDTO request) {
        
        authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    @GetMapping
    public ResponseEntity<List<UserGetResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetResponseDTO> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable Long id, @RequestBody @Valid UserUpdateRequestDTO user) {

        authService.updateUser(user, id);

        return ResponseEntity.noContent().build();
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        
        authService.deleteUser(id);

        return ResponseEntity.noContent().build();

    }
    
}