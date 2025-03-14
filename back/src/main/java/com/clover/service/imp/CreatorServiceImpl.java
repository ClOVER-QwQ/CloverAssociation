package com.clover.service.imp;

import com.clover.dao.CreatorDao;
import com.clover.pojo.Creator;
import com.clover.service.CreatorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CreatorServiceImpl implements CreatorService {
    @Autowired
    private CreatorDao creatorDao;

    @Override
    public PageInfo<Creator> getAll(String pageNum, String pageSize) {
        // 校验分页参数
        int page = pageNum != null && pageNum.matches("\\d+") ? Integer.parseInt(pageNum) : 1;
        int size = pageSize != null && pageSize.matches("\\d+") ? Integer.parseInt(pageSize) : 5; // 每页 5 条

        // 校验页码和每页大小是否合法
        if (page <= 0) {
            throw new IllegalArgumentException("页码必须大于0");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("每页大小必须大于0");
        }

        try {
            // 启动分页功能
            PageHelper.startPage(page, size);
            // 查询数据
            List<Creator> creators = creatorDao.getAll();
            // 封装分页信息并返回
            return new PageInfo<>(creators);
        } catch (Exception e) {
            // 异常处理逻辑
            throw new RuntimeException("查询开发者信息失败", e);
        }
    }

    @Override
    public List<Creator> getBasicAll() {
        return creatorDao.getBasicAll();
    }

    @Override
    public Creator getById(int id) {
        return creatorDao.getById(id);
    }

    @Override
    public Creator login(int uid, String password) {
        Creator creator = creatorDao.findUserByUserName(uid);

        if (creator != null && Objects.equals(password, creator.getPassword())) {
            return creator; // 如果密码匹配，返回用户信息
        }

        return null; // 如果密码不匹配或用户不存在，返回 null
    }

    @Override
    public int updateInfo(String username, String password, int id) {
        return creatorDao.updateInfo(username,password,id);
    }

    @Override
    public int updateAvatar(String avatar, int id) {
        return creatorDao.updateAvatar(id, avatar);
    }
}
