package com.rest.api.project.controller;

import com.rest.api.project.dto.FileUploadResponse;
import com.rest.api.project.dto.PostDto;
import com.rest.api.project.dto.PostRequestDto;
import com.rest.api.project.entity.FileDetails;
import com.rest.api.project.entity.Post;
import com.rest.api.project.service.FileUploadService;
import com.rest.api.project.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/createPost/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createPost(@PathVariable("userId") String userId, @ModelAttribute PostRequestDto postRequestDto) throws IOException {
        LOGGER.info("@@@ Inside Create Post ::: " + postRequestDto);
        postService.createPost(userId, postRequestDto);
        return new ResponseEntity<>(Map.of("status", "Created", "statusCode", 200, "message", "Post has been uploaded"), HttpStatus.CREATED);

    }
}
