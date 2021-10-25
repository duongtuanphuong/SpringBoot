package com.example.upload.service;

import java.util.List;

import com.example.upload.entity.Image;

public interface ImageService {
    public void save(Image img);

    public void delete(String uploadDir, String filename);

    List<Image> getAll();
    
}
