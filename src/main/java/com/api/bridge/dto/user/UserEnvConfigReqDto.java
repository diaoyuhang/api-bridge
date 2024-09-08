package com.api.bridge.dto.user;

import com.api.bridge.dao.domain.UserEnvConfig;
import com.api.bridge.dto.validGroup.Delete;
import com.api.bridge.dto.validGroup.Insert;
import com.api.bridge.dto.validGroup.Update;
import com.api.bridge.utils.SecretUtil;
import com.api.bridge.utils.UserHelperUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;

public class UserEnvConfigReqDto {

    @NotBlank(message = "id is null",groups = {Update.class, Delete.class})
    private String id;

    @NotBlank(message = "url is null",groups = {Insert.class, Update.class})
    private String url;

    private String description;

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

    public UserEnvConfig convertUserEnvConfig() {
        UserEnvConfig userEnvConfig = new UserEnvConfig();
        if (!StringUtils.isEmpty(this.id)){
            userEnvConfig.setId(Long.valueOf(SecretUtil.decrypt(this.id)));
        }

        userEnvConfig.setUserId(UserHelperUtil.getUser().getId());
        userEnvConfig.setDescription(this.description);
        userEnvConfig.setUrl(this.url);

        return userEnvConfig;
    }
}
