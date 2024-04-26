package com.api.bridge.dao;

import com.api.bridge.dao.domain.ApiMetaDate;
import org.apache.ibatis.annotations.Mapper;

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
}