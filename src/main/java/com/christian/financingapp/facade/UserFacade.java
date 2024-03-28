package com.christian.financingapp.facade;

import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.dto.user.UserIdDTO;
import com.christian.financingapp.domain.dto.user.UserToCreateDTO;
import com.christian.financingapp.facade.mapper.UserMapper;
import com.christian.financingapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFacade {

    private final UserMapper mapper;
    private final UserService service;

    @Transactional
    public UserIdDTO create(UserToCreateDTO dto) {
        User user = mapper.toEntity(dto);
        return mapper.toIdDto(service.create(user));
    }

}
