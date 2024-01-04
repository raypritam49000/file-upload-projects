package com.rest.api.project.service;

import com.rest.api.project.dto.FileUploadResponse;
import com.rest.api.project.entity.FileDetails;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileUploadService {
    public FileDetails uploadFile(MultipartFile file, String uploaderName) throws IOException;
    public Resource fetchFileAsResource(String fileName) throws FileNotFoundException;
    public List<FileDetails> getAllFiles();
}
