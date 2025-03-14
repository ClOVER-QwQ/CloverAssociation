package com.clover.dao;

import com.clover.pojo.Creator;
import com.clover.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDao {
    @Select("select * from clover_association.products")
    List<Product> selectProducts();

    @Select("select id,avatar,name from products")
    List<Product> getBasicAll();

    @Results(id = "ProductWithAuthorName", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "name", column = "name"),
            @Result(property = "authorId", column = "author_id"),
            @Result(property = "readme", column = "readme"),
            @Result(property = "description", column = "description"),
            @Result(property = "updateContent", column = "update_content"),
            @Result(property = "link", column = "link"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "authorName", column = "author_name", javaType = String.class)  // 确保 author_name 返回
    })
    @Select("SELECT p.*, a.name AS author_name " +
            "FROM products p " +
            "LEFT JOIN creators a ON p.author_id = a.id " +
            "WHERE p.id = #{id}")
    Map<String, Object> getById(@Param("id") int id);

    //creatorDao.getAll嵌套方法
    @Select("SELECT * FROM products WHERE author_id = #{creatorId}")
    List<Product> getProductsByCreatorId(@Param("creatorId") int creatorId);
}
