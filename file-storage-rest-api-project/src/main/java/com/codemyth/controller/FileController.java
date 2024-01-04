package com.codemyth.controller;

import com.codemyth.model.FileItem;
import com.codemyth.repository.FileItemRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class FileController {

    private FileItemRepository repository;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String FOLDER_PATH = "D:/uploads/";
        File filePath = new File(FOLDER_PATH);
        if (!filePath.exists()) {
            filePath.mkdir();
        }
        String fileName = new Date().getTime() + file.getOriginalFilename();
        String path = FOLDER_PATH + fileName;
        file.transferTo(new File(path).toPath());

        FileItem item = new FileItem(fileName, file.getContentType(), path);
        repository.save(item);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully to " + path);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Object> downloadFile(@PathVariable("filename") String filename) throws IOException {
        Optional<FileItem> fileItem = repository.findByFileName(filename);
        if (fileItem.isPresent()) {
            FileItem item = fileItem.get();
            String path = item.getFileLocation();
            byte[] content = Files.readAllBytes(Path.of(path));
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(content);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found for filename " + filename);
        }
    }
}
