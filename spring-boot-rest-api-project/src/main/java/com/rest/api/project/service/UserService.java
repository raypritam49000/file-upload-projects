package com.rest.api.project.service;

import com.rest.api.project.dto.UserDto;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public UserDto getUserById(String userId);
}
