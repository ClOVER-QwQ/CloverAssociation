package com.clover.controller;

import com.clover.pojo.User;
import com.clover.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController("UserController")
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @GetMapping("/log/{username}/{password}")
    public User log(@PathVariable("username") String username,@PathVariable("password") String password){
        return userService.login(username,password);
    }

    @PostMapping("reg/{username}/{password}")
    public int register(@PathVariable("username") String username,@PathVariable("password") String password){
        return userService.register(username,password);
    }

    @PutMapping("update/{id}/{username}/{description}")
    public int updateUserInfo(@PathVariable("username")String username,@PathVariable("description") String description,@PathVariable int id){
        return userService.updateUserInfo(username,description,id);
    }

    @PutMapping("update/{id}/{avatar}")
    public int updateAvatar(@PathVariable("avatar") String avatar,@PathVariable("id")  int id){
        return userService.updateAvatar(avatar,id);
    }
}
