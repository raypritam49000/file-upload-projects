package com.rest.file.upload.api.repository;

import com.rest.file.upload.api.entity.FileDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDetailsRepository extends JpaRepository<FileDetails,String> {
}
