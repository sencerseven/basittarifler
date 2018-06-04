package com.sencerseven.basittarifler.service;

public interface S3Services {
    public void downloadFile(String keyName);
    public void uploadFile(String keyName, String uploadFilePath);
}
