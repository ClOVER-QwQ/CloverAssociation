package com.clover.service;

import com.clover.pojo.User;

public interface UserService {
    User getUserById(int id);
    User login(String username,String password);
    int register(String username,String password);
    int updateUserInfo(String username,String password,int id);
    int updateAvatar(String avatar,int id);
}
