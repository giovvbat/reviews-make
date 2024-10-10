package com.giovanna.reviewsmake.domain.mapper;

import com.giovanna.reviewsmake.domain.dto.user.UserRecordDto;
import com.giovanna.reviewsmake.domain.model.UserModel;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T11:52:08-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserModel toEntity(UserRecordDto userRecordDto) {
        if ( userRecordDto == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setUsername( userRecordDto.username() );
        userModel.setEmail( userRecordDto.email() );
        userModel.setPassword( userRecordDto.password() );

        return userModel;
    }

    @Override
    public UserRecordDto toDto(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        String username = null;
        String email = null;
        String password = null;

        username = userModel.getUsername();
        email = userModel.getEmail();
        password = userModel.getPassword();

        UserRecordDto userRecordDto = new UserRecordDto( username, email, password );

        return userRecordDto;
    }
}
