package com.api.bridge.service.impl;

import com.api.bridge.dao.UserEnvConfigDao;
import com.api.bridge.dao.domain.UserEnvConfig;
import com.api.bridge.dto.user.UserEnvConfigReqDto;
import com.api.bridge.dto.user.UserEnvConfigResDto;
import com.api.bridge.service.UserEnvConfigService;
import com.api.bridge.utils.UserHelperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEnvConfigServiceImpl implements UserEnvConfigService {

    private final static Logger logger = LoggerFactory.getLogger(UserEnvConfigServiceImpl.class);

    @Autowired
    private UserEnvConfigDao userEnvConfigDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserEnvConfigResDto addConfig(UserEnvConfigReqDto userEnvConfigReqDto) {
        UserEnvConfig userEnvConfig = userEnvConfigReqDto.convertUserEnvConfig();

        UserHelperUtil.fillEditInfo(userEnvConfig);
        UserHelperUtil.fillCreateInfo(userEnvConfig);
        userEnvConfigDao.insert(userEnvConfig);

        return UserEnvConfigResDto.create(userEnvConfig);
    }

    @Override
    public void updateConfig(UserEnvConfigReqDto userEnvConfigReqDto) {
        UserEnvConfig newUserEnvConfig = userEnvConfigReqDto.convertUserEnvConfig();

        UserEnvConfig oldUserEnvConfig = userEnvConfigDao.selectByPrimaryKey(newUserEnvConfig.getId());
        oldUserEnvConfig.setUrl(newUserEnvConfig.getUrl());
        oldUserEnvConfig.setDescription(newUserEnvConfig.getDescription());
        UserHelperUtil.fillEditInfo(oldUserEnvConfig);

        userEnvConfigDao.updateByPrimaryKey(oldUserEnvConfig);
    }

    @Override
    public void deleteConfig(Long id) {
        userEnvConfigDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<UserEnvConfigResDto> getConfigs() {
        List<UserEnvConfig> userEnvConfigList = userEnvConfigDao.selectByUserId(UserHelperUtil.getUser().getId());
        return userEnvConfigList.stream().map(UserEnvConfigResDto::create).collect(Collectors.toList());
    }
}
