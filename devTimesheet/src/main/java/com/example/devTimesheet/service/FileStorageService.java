package com.example.devTimesheet.service;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public File saveFile(MultipartFile file) throws IOException;
}
