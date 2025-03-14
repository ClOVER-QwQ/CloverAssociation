package com.clover.dao;

import com.clover.pojo.Creator;
import com.clover.pojo.Product;
import com.clover.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreatorDao {
    @Results(id = "CreatorWithProducts", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "password", column = "password"),
            @Result(property = "birthDate", column = "birth_date"),
            @Result(property = "address", column = "address"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "email", column = "email"),
            @Result(property = "experience", column = "experience"),
            @Result(property = "skill", column = "skill"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "products", column = "id",
                    many = @Many(select = "com.clover.dao.ProductDao.getProductsByCreatorId"))
    })
    @Select("SELECT * FROM creators")
    List<Creator> getAll();

    @Select("select id,avatar,name from creators")
    List<Creator> getBasicAll();

    @Select("select * from creators where id = #{id}")
    Creator getById(@Param("id") int id);

    @Select("select * from creators where uid = #{uid}")
    Creator login(@Param("uid") int uid, String password);

    @Select("select * from creators where uid = #{uid}")
    Creator findUserByUserName(@Param("uid") int uid);

    @Update("update creators set name = #{name}, description = #{description} where id = #{id}")
    int updateInfo(String name, String description,int id);

    @Update("UPDATE users SET avatar = #{avatar} WHERE id = #{id}")
    int updateAvatar(@Param("id") int id, @Param("avatar") String avatar);
}
