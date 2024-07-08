package com.api.bridge.service;

import com.api.bridge.dao.domain.ApiMetaDate;
import com.api.bridge.dto.api.*;

import java.util.List;

public interface ApiMetaDateService {
    void addApiMetaDate(ApiMetaDateReqDto apiMetaDateReqDto);

    void deleteApiMetaDate(Long apiId);

    Long getProjectIdByApiId(Long apiId);

    List<PathInfoResDto> getPathInfo(String tagId);

    ApiMetaDate getMetaDateInfo(Long apiId);

    OpenApiBasicInfoResDto getBasicApiInfoList(Long projectId);

    List<ApiHistoryOperInfoResDTO> getApiHistoryInfo(Long apiId);
}
