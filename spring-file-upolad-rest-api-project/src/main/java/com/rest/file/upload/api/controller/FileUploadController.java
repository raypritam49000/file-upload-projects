package com.rest.file.upload.api.controller;

import com.rest.file.upload.api.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;


@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/uploadFiles")
    public ResponseEntity<Map<String,Object>> handleFileUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return new ResponseEntity<>(Map.of("message","file has been upload successfully","status",200,"success",true,"data",fileUploadService.uploadFile(multipartFile)),HttpStatus.OK);

    }
}
