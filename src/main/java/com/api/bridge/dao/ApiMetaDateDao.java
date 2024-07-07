package com.api.bridge.dao;

import com.api.bridge.dao.domain.ApiMetaDate;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ApiMetaDateDao {
    int deleteByPrimaryKey(Long id);

    int insert(ApiMetaDate record);

    int insertSelective(ApiMetaDate record);

    ApiMetaDate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiMetaDate record);

    int updateByPrimaryKey(ApiMetaDate record);

    ApiMetaDate selectByTagIdAndPathAndMethod(ApiMetaDate apiMetaDate);

    Long selectProjectIdByApiId(Long id);

    List<ApiMetaDate> selectPathInfoByTagId(String tagId);

    List<ApiMetaDate> selectBasicInfoByTagIds(Collection<String> tagIds);
}