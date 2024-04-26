package com.api.bridge.service;

import com.api.bridge.dto.api.ApiMetaDateReqDto;

public interface ApiMetaDateService {
    void addApiMetaDate(ApiMetaDateReqDto apiMetaDateReqDto);

    void deleteApiMetaDate(Long apiId);

    Long getProjectIdByApiId(Long apiId);
}
