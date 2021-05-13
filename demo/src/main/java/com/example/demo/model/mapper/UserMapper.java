package com.example.demo.model.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.request.CreateUserReq;

public class UserMapper {
    
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());       

        return tmp;
    }

    public static User toUser(CreateUserReq req){
        User user = new User();

        user.setName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());

        user.setPassword(req.getPassword());


        return user;
    }

  
}
