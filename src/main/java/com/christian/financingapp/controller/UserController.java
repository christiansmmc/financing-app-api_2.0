package com.christian.financingapp.controller;

import com.christian.financingapp.domain.dto.user.UserIdDTO;
import com.christian.financingapp.domain.dto.user.UserToCreateDTO;
import com.christian.financingapp.facade.UserFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade facade;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserIdDTO create(@RequestBody @Valid UserToCreateDTO dto) {
        return facade.create(dto);
    }
}
