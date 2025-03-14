package com.clover.service;

import com.clover.pojo.Creator;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CreatorService {
    PageInfo<Creator> getAll(String pageNum, String pageSize);
    List<Creator> getBasicAll();
    Creator getById(int id);
    Creator login(int uid, String password);
    int updateInfo(String username,String password,int id);
    int updateAvatar(String avatar,int id);
}
