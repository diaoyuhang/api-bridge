package com.api.bridge.service;

import com.api.bridge.dto.user.UserEnvConfigReqDto;
import com.api.bridge.dto.user.UserEnvConfigResDto;

import java.util.List;

public interface UserEnvConfigService {
    UserEnvConfigResDto addConfig(UserEnvConfigReqDto userEnvConfigReqDto);

    void updateConfig(UserEnvConfigReqDto userEnvConfigReqDto);

    void deleteConfig(Long id);

    List<UserEnvConfigResDto> getConfigs();

}
