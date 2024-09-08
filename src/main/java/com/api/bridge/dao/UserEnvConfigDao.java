package com.api.bridge.dao;


import com.api.bridge.dao.domain.UserEnvConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserEnvConfigDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserEnvConfig record);

    int insertSelective(UserEnvConfig record);

    UserEnvConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserEnvConfig record);

    int updateByPrimaryKey(UserEnvConfig record);

    List<UserEnvConfig> selectByUserId(Long userId);
}