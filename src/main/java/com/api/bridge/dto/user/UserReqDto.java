package com.api.bridge.dto.user;

import com.api.bridge.dao.domain.User;
import org.springframework.beans.BeanUtils;

public class UserReqDto {
    private String name;
    private String email;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convertUser() {
        User user = new User();
        BeanUtils.copyProperties(this,user);
        return user;
    }
}
