package com.api.bridge.dao;

import com.api.bridge.dao.domain.UserPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPermissionDao {
    int deleteByPrimaryKey(Long id);

    int insert(UserPermission record);

    int insertSelective(UserPermission record);

    UserPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPermission record);

    int updateByPrimaryKey(UserPermission record);

    void batchInsert(List<UserPermission> userPermissionList);

    int selectCountByProjectIdAndPathTypeAndUserId(@Param("projectId") Long projectId,@Param("pathType") Integer pathType,@Param("userId") Long userId);

    void deleteByUseIdAndPermissionId(@Param("userId")Long userId,@Param("permissionId") Long permissionId);

    int selectCountByTagIdAndPathTypeAndUserId(@Param("tagId")String tagId, @Param("pathType") Integer pathType, @Param("userId") Long userId);
}