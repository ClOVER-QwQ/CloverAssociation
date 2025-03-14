package com.clover.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class Creator {
    private int id;
    private String avatar;
    private String name;
    private  String description;
    private int uid;
    private String password;
    private Date birthDate;
    private String address;
    private String phone;
    private String email;
    private String experience;
    private String skill;
    private List<Product> products;
    private Date createdAt;  // 创建时间
    private Date updatedAt;  // 更新时间

    @Override
    public String toString() {
        return "Creator{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", uid=" + uid +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", experience='" + experience + '\'' +
                ", skill='" + skill + '\'' +
                ", products=" + products +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
