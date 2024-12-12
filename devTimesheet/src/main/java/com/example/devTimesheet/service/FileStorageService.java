package com.example.devTimesheet.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileStorageService {
    public File saveFile(MultipartFile file) throws IOException;
}
