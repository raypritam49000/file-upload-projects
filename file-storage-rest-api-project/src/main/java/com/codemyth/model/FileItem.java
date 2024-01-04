package com.codemyth.model;

import jakarta.persistence.*;

@Entity
@Table(name = "file_items")
public class FileItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String fileName;
    private String fileType;
    private String fileLocation;

    public FileItem() {
    }

    public FileItem(String fileName, String fileType, String fileLocation) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileLocation = fileLocation;
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

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Override
    public String toString() {
        return "FileItem{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                '}';
    }
}
