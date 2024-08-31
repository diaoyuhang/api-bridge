package com.api.bridge.dao;

import com.api.bridge.dao.domain.ProjectRequestParam;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRequestParamDao {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectRequestParam record);

    int insertSelective(ProjectRequestParam record);

    ProjectRequestParam selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectRequestParam record);

    int updateByPrimaryKey(ProjectRequestParam record);
}