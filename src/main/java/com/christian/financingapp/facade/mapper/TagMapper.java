package com.christian.financingapp.facade.mapper;

import com.christian.financingapp.domain.Tag;
import com.christian.financingapp.domain.dto.tag.TagDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TagMapper {

    TagDTO toDto(Tag entity);
}
