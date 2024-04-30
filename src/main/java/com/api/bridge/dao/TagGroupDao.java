package com.api.bridge.dao;


import com.api.bridge.dao.domain.TagGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagGroupDao {
    int deleteByPrimaryKey(String id);

    int insert(TagGroup record);

    int insertSelective(TagGroup record);

    TagGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TagGroup record);

    int updateByPrimaryKey(TagGroup record);

    List<TagGroup> selectByProjectId(Long projectId);
}