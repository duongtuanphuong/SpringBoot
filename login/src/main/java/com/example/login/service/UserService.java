package com.example.login.service;

import com.example.login.entity.User;
import com.example.login.model.request.CreateUserReq;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    
    public User createUser(CreateUserReq req);

    public Page<User> getAllUser(int page);

}
