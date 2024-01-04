package com.rest.api.project.dto;

import com.rest.api.project.entity.FileDetails;

import java.util.ArrayList;
import java.util.List;

public class PostDto {
    private String id;
    private String title;
    private String description;
    private List<FileDetails> fileDetails = new ArrayList<>();

    public PostDto(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public PostDto() {
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

    public List<FileDetails> getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(List<FileDetails> fileDetails) {
        this.fileDetails = fileDetails;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", fileDetails=" + fileDetails +
                '}';
    }
}
