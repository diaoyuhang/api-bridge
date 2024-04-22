package com.api.bridge.dao;

import com.api.bridge.dao.domain.PermissionPath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionPathDao {
    int deleteByPrimaryKey(Long id);

    int insert(PermissionPath record);

    int insertSelective(PermissionPath record);

    PermissionPath selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PermissionPath record);

    int updateByPrimaryKey(PermissionPath record);

    void batchInsert(List<PermissionPath> list);

    List<Long> selectIdByProjectId(Long projectId);

    List<Long> selectProjectIdByUserIdAndPathType(@Param("userId") Long userId,@Param("pathType") Integer pathType);
}