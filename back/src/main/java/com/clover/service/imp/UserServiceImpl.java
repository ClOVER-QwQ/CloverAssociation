package com.clover.service.imp;

import com.clover.dao.UserDao;
import com.clover.pojo.User;
import com.clover.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User login(String username, String password) {
        User user = userDao.findUserByUserName(username);

        if (user != null && Objects.equals(password, user.getPassword())) {
            return user; // 如果密码匹配，返回用户信息
        }

        return null; // 如果密码不匹配或用户不存在，返回 null
    }

    @Override
    public int register(String username, String password) {
        int result = 1; // 默认假设注册成功

        // 1. 检查用户名是否合法
        if (!isValidUsername(username)) {
            log.info("用户名不合法: {}", username);
            result = 0;
        }

        // 2. 检查密码是否合法
        if (password.length() < 6 || password.length() > 20) {
            log.info("密码格式不合法: {}", password);
            result = 0;
        }

        // 3. 检查用户名是否已存在
        User existingUser = userDao.findUserByUserName(username);
        if (existingUser != null) {
            log.info("用户名已存在: {}", username);
            result = 0;
        } else {
            log.info("用户名不存在: {}", username);
        }

        // 只有当所有检查都通过时，才执行注册操作
        if (result == 1) {
            userDao.register(username, password);
            log.info("用户注册成功: {}", username);
        }

        return result;
    }

    // 验证用户名格式
    private boolean isValidUsername(String username) {
        if (username.length() < 6 || username.length() > 20) {
            return false;
        }

        // 用户名只能包含字母、数字和下划线
        return username.matches("^[a-zA-Z0-9_]+$");
    }
    @Override
    public int updateUserInfo(String username, String password,int id) {
        return userDao.updateUser(username,password,id);
    }

    @Override
    public int updateAvatar(String avatar, int id) {
        return userDao.updateAvatar(id, avatar);
    }
}
