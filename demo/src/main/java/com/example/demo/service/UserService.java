package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.request.CreateUserReq;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    
    public User createUser(CreateUserReq req);

    public Page<User> getAllUser(int page);

}
