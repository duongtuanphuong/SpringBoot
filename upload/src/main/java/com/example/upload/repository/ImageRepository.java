package com.example.upload.repository;

import com.example.upload.entity.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    public Image findByLink(String link);
}
