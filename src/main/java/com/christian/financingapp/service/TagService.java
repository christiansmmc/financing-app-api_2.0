package com.christian.financingapp.service;

import com.christian.financingapp.domain.Tag;
import com.christian.financingapp.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public List<Tag> findAll() {
        return repository.findAll();
    }
}
