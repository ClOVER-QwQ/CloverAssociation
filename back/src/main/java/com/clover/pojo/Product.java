package com.clover.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class Product {
    private int id;  // 主键
    private String avatar;
    private String name;  // 产品名称
    private int authorId;
    private String readme;
    private String description;  // 产品描述
    private String updateContent; //更新内容
    private String link;  // 产品链接
    private Date createdAt;  // 创建时间
    private Date updatedAt;  // 更新时间

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", readme='" + readme + '\'' +
                ", description='" + description + '\'' +
                ", updateContent='" + updateContent + '\'' +
                ", link='" + link + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
