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

    /**
     * 检查用户名和密码
     */
    @Override
    public boolean checkUserAndPasswd(String username, String password) {
        UserEntity user =  userMapper.findItem(username,password);
        return user != null;
    }

    /**
     * 检查账户是否存在
     */
    @Override
    public boolean checkUser(String username) {
        UserEntity user =  userMapper.findUser(username);
        return user != null;
    }

    @Override
    public void update(UserEntity user) {
        userMapper.update(user);
    }

    
}