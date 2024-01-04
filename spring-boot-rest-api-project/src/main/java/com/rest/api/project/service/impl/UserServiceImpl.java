package com.rest.api.project.service.impl;

import com.rest.api.project.dto.PostDto;
import com.rest.api.project.dto.UserDto;
import com.rest.api.project.entity.Post;
import com.rest.api.project.entity.User;
import com.rest.api.project.exceptions.ResourceNotFoundException;
import com.rest.api.project.mappers.UserMapper;
import com.rest.api.project.repository.UserRepository;
import com.rest.api.project.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return UserMapper.INSTANCE.toDto(userRepository.save(UserMapper.INSTANCE.toEntity(userDto)));
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found with given id : " + userId));

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setId(user.getId());

        List<PostDto> postDtoList = user.getPosts().stream().map(post -> {
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setDescription(post.getDescription());
            postDto.setTitle(post.getTitle());
            postDto.setFileDetails(post.getFileDetails());
            return postDto;
        }).toList();

        userDto.setPosts(postDtoList);

        return userDto;
    }
}
