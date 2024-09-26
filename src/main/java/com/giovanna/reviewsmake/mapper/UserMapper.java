package com.giovanna.reviewsmake.mapper;

import com.giovanna.reviewsmake.dto.user.UserRecordDto;
import com.giovanna.reviewsmake.model.UserModel;
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

