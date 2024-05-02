package com.api.bridge.dao;


import com.api.bridge.dao.domain.Project;
import com.api.bridge.dao.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectDao {
    int deleteByPrimaryKey(@Param("id") Long id,@Param("editor") String editor);

    int insert(Project record);

    Project selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    Integer selectCountByPrimaryKey(Long projectId);

    List<Project> selectByIds(List<Long> projectIds);
}