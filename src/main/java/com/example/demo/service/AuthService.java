package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.VO.JwtAuthenticationResponse;
import com.example.demo.modal.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.security.UserPrincipal;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtAuthenticationResponse authenticateUser(User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

//        log.info("User with [email: {}] has logged in", userPrincipal.getUsername());


        return new JwtAuthenticationResponse(jwt);
    }

    public Long registerUser(User signUpRequest) {

        User user = new User();
        user.setName(signUpRequest.getName());

        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

//        log.info("Successfully registered user with [email: {}]", user.getEmail());

        return userRepository.save(user).getId();
    }

}
