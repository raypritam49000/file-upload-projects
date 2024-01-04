package com.rest.api.project.service.impl;

import com.rest.api.project.dto.PostDto;
import com.rest.api.project.dto.PostRequestDto;
import com.rest.api.project.entity.FileDetails;
import com.rest.api.project.entity.Post;
import com.rest.api.project.entity.User;
import com.rest.api.project.exceptions.ResourceNotFoundException;
import com.rest.api.project.repository.PostRepository;
import com.rest.api.project.repository.UserRepository;
import com.rest.api.project.service.FileUploadService;
import com.rest.api.project.service.PostService;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;

    public PostServiceImpl(PostRepository postRepository,UserRepository userRepository,FileUploadService fileUploadService) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.fileUploadService = fileUploadService;
    }

    @Override
    public void createPost(String userId, PostRequestDto postRequestDto) throws IOException {

        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Not found with given id : "+userId));

        Post post = new Post();
        post.setDescription(postRequestDto.getDescription());
        post.setTitle(postRequestDto.getTitle());

        if(Objects.nonNull(user)){
            post.setUser(user);
        }

        if(postRequestDto.getFiles().length>0){
            List<FileDetails> fileDetails = Arrays.stream(postRequestDto.getFiles()).map(file -> {
                try {
                    return fileUploadService.uploadFile(file, file.getOriginalFilename());
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }).toList();

            post.setFileDetails(fileDetails);
        }

        postRepository.save(post);

    }
}
