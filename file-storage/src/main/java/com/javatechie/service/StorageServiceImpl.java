package com.javatechie.service;

import com.javatechie.entity.FileData;
import com.javatechie.entity.ImageData;
import com.javatechie.respository.FileDataRepository;
import com.javatechie.respository.StorageRepository;
import com.javatechie.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository repository;

    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH = "D:\\uploads";

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = repository.save(new ImageData(file.getOriginalFilename(), file.getContentType(), ImageUtils.compressImage(file.getBytes())));
        return "file uploaded successfully : " + file.getOriginalFilename();
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    @Override
    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String filePath = FOLDER_PATH + File.separator + file.getOriginalFilename();
        FileData fileData = fileDataRepository.save(new FileData(file.getOriginalFilename(), file.getContentType(), filePath));
        file.transferTo(new File(filePath));
        return "file uploaded successfully : " + filePath;
    }

    @Override
    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        String filePath = fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
