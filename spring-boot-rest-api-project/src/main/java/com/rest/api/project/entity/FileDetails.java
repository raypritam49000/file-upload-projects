package com.rest.api.project.entity;

import jakarta.persistence.*;

@Table
@Entity
public class FileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String fileName;

    @Column
    private String fileType;

    @Column
    private String fileUri;
    @Column
    private String fileDownloadUri;
    @Column
    private long fileSize;
    @Column
    private String uploaderName;

    public FileDetails() {
    }

    public FileDetails(String id, String fileName, String fileType, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public FileDetails(String fileName, String fileType, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
    }

    @Override
    public String toString() {
        return "FileDetails{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileUri='" + fileUri + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileSize=" + fileSize +
                ", uploaderName='" + uploaderName + '\'' +
                '}';
    }
}
