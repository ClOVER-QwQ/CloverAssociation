package com.clover.controller;

import com.clover.pojo.Creator;
import com.clover.service.CreatorService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController("creatorController")
@RequestMapping("creator")
public class CreatorController {
    @Autowired
    private CreatorService creatorService;

    @GetMapping("/getAll/{pageNum}/{pageSize}")
    public PageInfo<Creator> getAll(@PathVariable String pageNum, @PathVariable String pageSize) {
        return creatorService.getAll(pageNum, pageSize);
    }

    @GetMapping("/getBasicAll")
    public List<Creator> getAllBasic() {return creatorService.getBasicAll();}

    @GetMapping("/get/{id}")
    public Creator getById(@PathVariable int id) {return creatorService.getById(id);}

    @GetMapping("/login/{uid}/{password}")
    public Creator login(@PathVariable int uid, @PathVariable String password) {
        return creatorService.login(uid, password);
    }

    @PutMapping("update/{id}/{name}/{description}")
    public int updateUserInfo(@PathVariable("name")String name,@PathVariable("description") String description,@PathVariable int id){
        return creatorService.updateInfo(name,description,id);
    }

    @PutMapping("update/{id}/{avatar}")
    public int updateAvatar(@PathVariable("avatar") String avatar,@PathVariable("id")  int id){
        return creatorService.updateAvatar(avatar,id);
    }
}