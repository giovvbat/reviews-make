package com.giovanna.reviewsmake.domain.mapper;

import com.giovanna.reviewsmake.domain.model.UserModel;
import com.giovanna.reviewsmake.domain.dto.user.UserRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper instance = Mappers.getMapper(UserMapper.class);

    /*mapping from dto to model*/
    UserModel toEntity(UserRecordDto userRecordDto);

    /*mapping from model to dto*/
    UserRecordDto toDto(UserModel userModel);
}

