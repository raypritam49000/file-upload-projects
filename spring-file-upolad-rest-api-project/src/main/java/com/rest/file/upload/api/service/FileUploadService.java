package com.rest.file.upload.api.service;

import com.rest.file.upload.api.entity.FileDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    public FileDetails uploadFile(MultipartFile file) throws IOException;
}
