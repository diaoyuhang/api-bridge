package com.api.bridge.dto.user;

import com.api.bridge.dao.domain.User;
import org.springframework.beans.BeanUtils;

public class UserResDto {
    private String name;
    private String email;

    private String authentication;

    public static UserResDto create(User user) {
        UserResDto userResDto = new UserResDto();
        BeanUtils.copyProperties(user,userResDto);
        return userResDto;
    }

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

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
