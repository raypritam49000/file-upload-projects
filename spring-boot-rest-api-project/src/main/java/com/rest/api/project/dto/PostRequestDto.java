package com.rest.api.project.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class PostRequestDto {
    private String id;
    private String title;
    private String description;
    private MultipartFile[] files;

    public PostRequestDto() {
    }

    public PostRequestDto(String id, String title, String description, MultipartFile[] files) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.files = files;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "PostRequestDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
