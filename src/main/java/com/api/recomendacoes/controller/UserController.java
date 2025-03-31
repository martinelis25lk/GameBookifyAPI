package com.api.recomendacoes.controller;

import com.api.recomendacoes.domain.user.User;
import com.api.recomendacoes.domain.user.UserRequestDTO;
import com.api.recomendacoes.domain.user.UserRetrieveDTO;
import com.api.recomendacoes.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users") // Base URL for user-related endpoints
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        User createdUser = usersService.createUser(userRequestDTO);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserRetrieveDTO> getUser(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.findUserById(id));
    }
}
