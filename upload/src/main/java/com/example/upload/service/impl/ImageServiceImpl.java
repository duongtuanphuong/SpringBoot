package com.example.upload.service.impl;

import java.io.File;
import java.util.List;

import com.example.upload.entity.Image;
import com.example.upload.exception.BadRequestException;
import com.example.upload.exception.InternalServerException;
import com.example.upload.repository.ImageRepository;
import com.example.upload.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void save(Image img) {
        // TODO Auto-generated method stub
        imageRepository.save(img);
        
    }

    @Override
    @Transactional(rollbackFor = InternalServerException.class)
    public void delete(String uploadDir, String filename) {
        // TODO Auto-generated method stub
        String link = "/media/static" +filename;    
        Image img = imageRepository.findByLink(link);
        if(img == null){
            throw new BadRequestException("File không tồn tại");
        }
        // Integer inUse = imageRepository.checkImgInUse(link);
        // if (inUse != null) {
        //     throw new BadRequestException("Ảnh đã được sử dụng không thể xóa");
        // }       
        imageRepository.delete(img); 
        File file = new File(uploadDir + "/" +filename);
        if(file.exists()){
            if(!file.delete()){
                throw new InternalServerException("Lỗi xóa ảnh");
            }
        }
    }

    @Override
    public List<Image> getAll() {
        // TODO Auto-generated method stub
        return imageRepository.findAll();
    }

    
    
}
