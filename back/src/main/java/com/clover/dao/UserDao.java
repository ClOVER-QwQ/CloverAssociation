package com.clover.dao;

import com.clover.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    @Select("select * from users where id = #{id}")
    User getUserById(@Param("id") int id);

    @Select("select * from users where username=#{username}")
    User findUserByUserName(@Param("username") String username);

    @Select("select * from users where username = #{username}")
    User login(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO users (username,password) VALUES (#{username},#{password})")
    void register(@Param("username") String username, @Param("password") String password);

    @Insert("INSERT INTO users (avatar,username, password, description) " +
            "VALUES (#{avatar},#{username}, #{password}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @Update("update users set username = #{username}, description = #{description} where id = #{id}")
    int updateUser(String username, String description,int id);

    @Update("UPDATE users SET avatar = #{avatar} WHERE id = #{id}")
    int updateAvatar(@Param("id") Integer id, @Param("avatar") String avatar);
}
