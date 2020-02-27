package com.example.uploadingfiles.LoginService;

import com.example.uploadingfiles.data.UserEntity;
import com.example.uploadingfiles.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;
    
    @Override
    public void add(UserEntity user) {
        userMapper.insert(user);

    }

    
}