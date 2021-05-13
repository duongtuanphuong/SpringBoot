package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.model.request.CreateUserReq;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(CreateUserReq req) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<User> getAllUser(int page) {

        Page<User> user = userRepository.findAll(PageRequest.of(page, 8, Sort.by("id").descending()));

        return user;
    }
    
}
