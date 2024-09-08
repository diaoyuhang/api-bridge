package com.api.bridge.dto.user;

import com.api.bridge.dao.domain.UserEnvConfig;
import com.api.bridge.utils.SecretUtil;

public class UserEnvConfigResDto {

    private String id;

    private String url;

    private String description;

    public static UserEnvConfigResDto create(UserEnvConfig userEnvConfig) {
        UserEnvConfigResDto userEnvConfigResDto = new UserEnvConfigResDto();
        userEnvConfigResDto.setId(SecretUtil.encrypt(userEnvConfig.getId().toString()));
        userEnvConfigResDto.setUrl(userEnvConfig.getUrl());
        userEnvConfigResDto.setDescription(userEnvConfig.getDescription());

        return userEnvConfigResDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
