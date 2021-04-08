package com.example.login.service.impl;

import com.example.login.entity.User;
import com.example.login.exception.DuplicateRecordException;
import com.example.login.model.mapper.UserMapper;
import com.example.login.model.request.CreateUserReq;
import com.example.login.repository.UserRepository;
import com.example.login.service.UserService;

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
        User user = userRepository.findByEmail(req.getEmail());
        if(user !=null){
            throw new DuplicateRecordException("Email đã tồn tại");
        }

        user = UserMapper.toUser(req);

        userRepository.save(user);

        return user;
    }

    @Override
    public Page<User> getAllUser(int page) {
        // TODO Auto-generated method stub
        Page<User> user = userRepository.findAll(PageRequest.of(page, 8, Sort.by("id").descending()));

        return user;
    }
    
}
