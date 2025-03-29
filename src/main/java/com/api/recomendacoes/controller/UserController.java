package com.api.recomendacoes.controller;

import com.api.recomendacoes.domain.user.UserRequestDTO;
import com.api.recomendacoes.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users") // Base URL for user-related endpoints
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public Integer createUser(@RequestBody UserRequestDTO userRequestDTO) {
        return usersService.createUser(userRequestDTO);
    }

    @GetMapping
    public String getUser() {
        return "User listing";
    }
}
