package com.she.blog.user.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class UserDto {

    private Long id;
    private String userName;
    private String email;
    private String phone;
    private Boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLock;
    private boolean credentialsNonExpired;
    private LocalTime createdAt;
    private LocalTime updatedAt;
}
