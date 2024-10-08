package com.api.bridge.dao;

import com.api.bridge.dao.domain.ProjectRequestParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectRequestParamDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectRequestParam record);

    int insertSelective(ProjectRequestParam record);

    ProjectRequestParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectRequestParam record);

    int updateByPrimaryKey(ProjectRequestParam record);

    List<ProjectRequestParam> selectByProjectId(Long projectId);

    ProjectRequestParam selectByIdAndProjectId(@Param("id") Long id,@Param("projectId") Long projectId);
}