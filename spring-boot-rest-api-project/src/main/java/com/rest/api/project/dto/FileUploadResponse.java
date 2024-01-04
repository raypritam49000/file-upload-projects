package com.rest.api.project.dto;


public class FileUploadResponse {

    private String id;
    private String fileName;
    private String fileType;
    private String fileUri;
    private String fileDownloadUri;
    private long fileSize;
    private String uploaderName;

    public FileUploadResponse(String id, String fileName, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {
        this.id = id;
        this.fileName = fileName;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public FileUploadResponse(String id, String fileName, String fileType, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public FileUploadResponse() {
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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FileUploadResponse{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileUri='" + fileUri + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileSize=" + fileSize +
                ", uploaderName='" + uploaderName + '\'' +
                '}';
    }
}
