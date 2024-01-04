package com.rest.api.project.mappers;

import com.rest.api.project.dto.UserDto;
import com.rest.api.project.entity.User;
import com.rest.api.project.mappers.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends BaseMapper<UserDto, User> {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
