package com.enigma.mevents.doa.mappers;

import com.enigma.mevents.doa.dtos.UserDto;
import com.enigma.mevents.doa.entities.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapToUserEntity(UserDto userDto);

    UserDto mapTotoUserDto(User user);


}