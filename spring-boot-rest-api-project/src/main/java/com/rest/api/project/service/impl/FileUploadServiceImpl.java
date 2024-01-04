package com.rest.api.project.service.impl;

import com.rest.api.project.dto.FileUploadResponse;
import com.rest.api.project.dto.UserDto;
import com.rest.api.project.entity.FileDetails;
import com.rest.api.project.exceptions.FileNotSupportedException;
import com.rest.api.project.repository.FileDetailsRepository;
import com.rest.api.project.repository.PostRepository;
import com.rest.api.project.repository.UserRepository;
import com.rest.api.project.service.FileUploadService;
import com.rest.api.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileDetailsRepository fileDetailsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostRepository postRepository;

    private final Path UPLOAD_PATH = Paths.get(new ClassPathResource("").getFile()
            .getAbsolutePath() + File.separator + "static" + File.separator + "image");

    public FileUploadServiceImpl() throws IOException {
    }

    @Override
    public FileDetails uploadFile(MultipartFile file, String uploaderName) throws IOException {
        System.out.println("@@@ UPLOAD_PATH ::: " + UPLOAD_PATH);

        if (!Files.exists(UPLOAD_PATH)) {
            Files.createDirectories(UPLOAD_PATH);
        }

        // file format validation
        if (!Objects.equals(file.getContentType(), "image/jpeg") && !Objects.equals(file.getContentType(), "image/png")) {
            throw new FileNotSupportedException("only .jpeg and .png images are " + "supported");
        }

        String timeStampedFileName = new SimpleDateFormat("ssmmHHddMMyyyy").format(new Date()) + "_" + file.getOriginalFilename();

        System.out.println("@@@ timeStampedFileName ::: " + timeStampedFileName);

        Path filePath = UPLOAD_PATH.resolve(timeStampedFileName);

        System.out.println("@@@ filePath ::: " + filePath);

        Files.copy(file.getInputStream(), filePath);

        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/").path(timeStampedFileName).toUriString();

        String fileDownloadUri =
                ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/file/download/").path(timeStampedFileName).toUriString();

        FileDetails fileDetails = new FileDetails(file.getOriginalFilename(), file.getContentType(), fileUri, fileDownloadUri, file.getSize(), file.getOriginalFilename());


        FileDetails createFileDetails = this.fileDetailsRepository.save(fileDetails);
        return createFileDetails;
    }

    @Override
    public Resource fetchFileAsResource(String fileName) throws FileNotFoundException {

        try {
            Path filePath = UPLOAD_PATH.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }


    @Override
    public List<FileDetails> getAllFiles() {
        return this.fileDetailsRepository.findAll();
    }
}
