package com.rest.file.upload.api.service.impl;

import com.rest.file.upload.api.entity.FileDetails;
import com.rest.file.upload.api.exceptions.BadRequestException;
import com.rest.file.upload.api.repository.FileDetailsRepository;
import com.rest.file.upload.api.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileDetailsRepository fileDetailsRepository;

    private final Path UPLOAD_PATH = Paths.get(new ClassPathResource("").getFile().getAbsolutePath() + File.separator + "static" + File.separator + "image");

    public FileUploadServiceImpl() throws IOException {
    }

    @Override
    public FileDetails uploadFile(MultipartFile multipartFile) {
        try {

            if (!Files.exists(UPLOAD_PATH)) {
                Files.createDirectories(UPLOAD_PATH);
            }

            if (multipartFile.isEmpty()) {
                throw new BadRequestException("Request must be contain file");
            }

            if (!Objects.equals(multipartFile.getContentType(), "image/jpeg")) {
                throw new BadRequestException("Only JPEG content type are allow");
            }

            Path filePath = UPLOAD_PATH.resolve(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(Objects.requireNonNull(multipartFile.getOriginalFilename())).toUriString();

            FileDetails fileDetails = new FileDetails(multipartFile.getOriginalFilename(), multipartFile.getContentType(), String.valueOf(multipartFile.getSize()), fileUri);

            return fileDetailsRepository.save(fileDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
