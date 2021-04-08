package com.example.login.model.mapper;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.login.entity.User;
import com.example.login.model.dto.UserDto;
import com.example.login.model.request.CreateUserReq;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {
    
    public static UserDto toUserDto(User user){
        UserDto tmp = new UserDto();

        tmp.setId(user.getId());
        tmp.setName(user.getName());
        tmp.setEmail(user.getEmail());
        tmp.setPhone(user.getPhone());
        tmp.setRoles(user.getRole());

        return tmp;
    }

    public static User toUser(CreateUserReq req){
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());


        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()); // Mã hóa mật khẩu

        user.setPassword(hash);
        
        user.setRole(new ArrayList<String>(Arrays.asList("USER")));


        return user;
    }

}
