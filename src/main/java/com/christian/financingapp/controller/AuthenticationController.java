package com.christian.financingapp.controller;

import com.christian.financingapp.config.JwtConfig;
import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.dto.AuthenticationRequestDTO;
import com.christian.financingapp.domain.dto.AuthenticationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthenticationResponseDTO authenticate(@RequestBody @Valid AuthenticationRequestDTO dto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
        );
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        String token = jwtConfig.generateToken((User) auth.getPrincipal());
        return new AuthenticationResponseDTO(token);
    }
}
