package com.api.bridge.dao;

import com.api.bridge.dao.domain.PermissionPath;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermissionPathDao {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionPath record);

    int insertSelective(PermissionPath record);

    PermissionPath selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionPath record);

    int updateByPrimaryKey(PermissionPath record);
}