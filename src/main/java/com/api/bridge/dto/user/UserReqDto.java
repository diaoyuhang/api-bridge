package com.api.bridge.dto.user;

import com.api.bridge.dao.domain.User;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Select;
import com.api.bridge.dto.validGroup.Update;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

public class UserReqDto {
    @NotBlank(message = "name is empty",groups = {Insert.class, Update.class})
    private String name;
    @NotBlank(message = "email is empty",groups = {Insert.class, Select.class,Update.class})
    private String email;
    @NotBlank(message = "password is empty",groups = {Insert.class,Select.class, Update.class})
    private String password;
    @NotBlank(message = "newPassword is empty",groups = {Update.class})
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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
