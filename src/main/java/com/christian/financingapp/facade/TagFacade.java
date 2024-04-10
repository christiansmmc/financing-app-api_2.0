package com.christian.financingapp.facade;

import com.christian.financingapp.domain.dto.tag.TagDTO;
import com.christian.financingapp.facade.mapper.TagMapper;
import com.christian.financingapp.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagFacade {

    private final TagMapper mapper;
    private final TagService service;

    @Transactional
    public List<TagDTO> findAll() {
        return service.findAll()
                .stream().map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
