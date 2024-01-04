package com.rest.api.project.service;

import com.rest.api.project.dto.PostDto;
import com.rest.api.project.dto.PostRequestDto;

import java.io.IOException;

public interface PostService {
    public void createPost(String userId, PostRequestDto postDto) throws IOException;
}
