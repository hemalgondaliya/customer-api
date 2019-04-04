package com.example.demo.controller;

import com.example.demo.modal.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

import com.example.demo.modal.User;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    @ResponseStatus(OK)
    public ResponseEntity login(@Valid @RequestBody User loginRequest) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth", authService.authenticateUser(loginRequest).getAccessToken());
        return new ResponseEntity(new Response("Login Success",200), headers, HttpStatus.OK);
    }
}

