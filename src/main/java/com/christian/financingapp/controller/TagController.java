package com.christian.financingapp.controller;

import com.christian.financingapp.domain.dto.tag.TagDTO;
import com.christian.financingapp.facade.TagFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagFacade facade;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> findAll() {
        return facade.findAll();
    }
}
