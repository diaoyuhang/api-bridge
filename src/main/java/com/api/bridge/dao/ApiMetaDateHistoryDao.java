package com.api.bridge.dao;

import com.api.bridge.dao.domain.ApiMetaDateHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMetaDateHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(ApiMetaDateHistory record);

    int insertSelective(ApiMetaDateHistory record);

    ApiMetaDateHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ApiMetaDateHistory record);

    int updateByPrimaryKey(ApiMetaDateHistory record);

    List<ApiMetaDateHistory> selectByApiIdDescEditTime(Long apiId);
}