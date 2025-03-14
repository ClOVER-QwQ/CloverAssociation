package com.clover.pojo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private int id;
    private String avatar;
    private String description;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
