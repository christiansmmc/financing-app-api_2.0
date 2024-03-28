package com.christian.financingapp.facade.mapper;

import com.christian.financingapp.domain.User;
import com.christian.financingapp.domain.dto.user.UserIdDTO;
import com.christian.financingapp.domain.dto.user.UserToCreateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toEntity(UserToCreateDTO dto);

    UserIdDTO toIdDto(User user);
}
