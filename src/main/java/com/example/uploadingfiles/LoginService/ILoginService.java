package com.example.uploadingfiles.LoginService;

import com.example.uploadingfiles.data.UserEntity;

public interface ILoginService{
    public void add(UserEntity user);

    public boolean checkUserAndPasswd(String username, String password);
    
    public boolean checkUser(String username);

	public void update(UserEntity user);
}