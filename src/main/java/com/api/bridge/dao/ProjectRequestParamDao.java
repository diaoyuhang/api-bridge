package com.api.bridge.dao;

import com.api.bridge.dao.domain.ProjectRequestParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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