package com.api.bridge.dao;

import com.api.bridge.dao.domain.UserPermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);
}